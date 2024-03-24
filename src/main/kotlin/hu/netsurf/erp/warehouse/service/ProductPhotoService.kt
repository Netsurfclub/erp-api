package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.constants.FileConstants.PRODUCTS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.util.FileUtils
import hu.netsurf.erp.warehouse.util.FileValidator
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductPhotoService(
    private val productService: ProductService,
    private val fileUtils: FileUtils,
    private val fileValidator: FileValidator,
) {
    fun uploadPhoto(id: Int, file: MultipartFile): String? {
        fileValidator.validate(file)

        val product = productService.getProduct(id)
        if (product.photo != null) {
            throw ProductAlreadyHasPhotoUploadedException(id)
        }

        product.photo = fileUtils.storePhoto(
            file = file,
            directoriesPath = fileUtils.createUploadsDirectoryStructureIfNotExists(PRODUCTS_SUBDIRECTORY_NAME),
        )
        productService.updateProduct(product)

        return product.photo
    }
}

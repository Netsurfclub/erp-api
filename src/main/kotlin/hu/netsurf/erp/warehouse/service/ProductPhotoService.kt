package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.constants.FileConstants.PRODUCTS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductPhoto
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
    fun upload(file: MultipartFile, id: Int): ProductPhoto {
        fileValidator.validate(file)

        val product = productService.getProduct(id)

        if (product.photo != null) {
            throw ProductAlreadyHasPhotoUploadedException(id)
        }

        val directoriesPath = fileUtils.createUploadsDirectoryStructureIfNotExists(
            customSubdirectoryName = PRODUCTS_SUBDIRECTORY_NAME,
        )

        val photoFileName = fileUtils.storePhoto(file, directoriesPath)
        // product.InitializePhoto(fileName)

        // return product.photo
        return ProductPhoto(1, "", "", 1L, "", 1, Product()) // TODO: Remove this.
    }
}

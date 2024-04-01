package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.MULTIPART_FILE_VALIDATED_SUCCESSFULLY
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.MULTIPART_FILE
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.constant.FileConstants.PRODUCTS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.extension.asString
import hu.netsurf.erp.warehouse.util.FileUtils
import hu.netsurf.erp.warehouse.util.FileValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductPhotoService(
    private val productService: ProductService,
    private val fileUtils: FileUtils,
    private val fileValidator: FileValidator,
) {
    private val logger: Logger = LoggerFactory.getLogger(ProductPhotoService::class.java)

    fun uploadPhoto(productId: Int, file: MultipartFile): String? {
        fileValidator.validate(file)

        logger.logInfo(
            MULTIPART_FILE_VALIDATED_SUCCESSFULLY,
            mapOf(MULTIPART_FILE to file.asString()),
        )

        val product = productService.getProduct(productId)
        if (product.photo != null) {
            throw ProductAlreadyHasPhotoUploadedException(productId)
        }

        val directoriesPath = fileUtils.createPhotoUploadsDirectoryStructure(PRODUCTS_SUBDIRECTORY_NAME)
        product.photo = fileUtils.storePhoto(file, directoriesPath)
        productService.updateProduct(product)

        return product.photo
    }
}

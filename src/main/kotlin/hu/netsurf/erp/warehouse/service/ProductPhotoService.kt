package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.common.extension.logError
import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.FileConstants.PRODUCTS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.constant.LogEventConstants.EXCEPTION_OCCURRED_DURING_RETRIEVING_PRODUCT_PHOTO_FROM_FILE_SYSTEM
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PHOTO_FILE_VALIDATED_SUCCESSFULLY
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PHOTO_FILE
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.warehouse.util.file.FileUtils
import hu.netsurf.erp.warehouse.util.validation.FileValidator
import hu.netsurf.erp.warehouse.wrapper.PhotoFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductPhotoService(
    private val productService: ProductService,
    private val fileUtils: FileUtils,
    private val fileValidator: FileValidator,
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getProductPhoto(fileName: String): ByteArray {
        val productPhoto: ByteArray

        try {
            productPhoto = fileUtils.readAllBytes(PRODUCTS_SUBDIRECTORY_NAME, fileName)
        } catch (exception: Exception) {
            logger.logError(
                EXCEPTION_OCCURRED_DURING_RETRIEVING_PRODUCT_PHOTO_FROM_FILE_SYSTEM,
                exception = exception,
            )

            throw ProductPhotoNotFoundException(fileName)
        }

        return productPhoto
    }

    fun uploadProductPhoto(
        productId: Long,
        file: PhotoFile,
    ): String? {
        fileValidator.validate(file)

        logger.logInfo(
            PHOTO_FILE_VALIDATED_SUCCESSFULLY,
            mapOf(PHOTO_FILE to file.asString()),
        )

        val product = productService.getProduct(productId)
        if (product.photo != null) {
            throw ProductAlreadyHasPhotoUploadedException(productId)
        }

        val directoryStructurePath = fileUtils.createPhotoUploadsDirectoryStructure(PRODUCTS_SUBDIRECTORY_NAME)
        product.photo = fileUtils.storePhoto(file, directoryStructurePath)
        productService.updateProduct(product)

        return product.photo
    }
}

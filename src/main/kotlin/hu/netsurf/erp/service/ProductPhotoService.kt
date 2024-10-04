package hu.netsurf.erp.service

import hu.netsurf.erp.constant.FileConstants.PRODUCTS_SUBDIRECTORY_NAME
import hu.netsurf.erp.constant.LogEventConstants.MULTIPART_FILE_VALIDATED_SUCCESSFULLY
import hu.netsurf.erp.constant.LoggerConstants.MULTIPART_FILE
import hu.netsurf.erp.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.extension.asString
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.util.FileUtils
import hu.netsurf.erp.util.FileValidator
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
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getProductPhoto(fileName: String): ByteArray {
        val productPhoto: ByteArray

        try {
            productPhoto = fileUtils.readAllBytes(PRODUCTS_SUBDIRECTORY_NAME, fileName)
        } catch (exception: Exception) {
            throw ProductPhotoNotFoundException(fileName)
        }

        return productPhoto
    }

    fun uploadProductPhoto(
        productId: Int,
        file: MultipartFile,
    ): String? {
        fileValidator.validate(file)

        logger.logInfo(
            MULTIPART_FILE_VALIDATED_SUCCESSFULLY,
            mapOf(MULTIPART_FILE to file.asString()),
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

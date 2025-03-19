package hu.netsurf.erp.controller

import hu.netsurf.erp.constant.FileConstants.IMAGE
import hu.netsurf.erp.constant.LogEventConstants.GET_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.GET_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.GET_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.MULTIPART_FILE_MAPPED_TO_PHOTO_FILE
import hu.netsurf.erp.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LoggerConstants.CONTENT_TYPE
import hu.netsurf.erp.constant.LoggerConstants.FILE_NAME
import hu.netsurf.erp.constant.LoggerConstants.MULTIPART_FILE
import hu.netsurf.erp.constant.LoggerConstants.PHOTO_FILE
import hu.netsurf.erp.constant.LoggerConstants.PRODUCT_ID
import hu.netsurf.erp.constant.LoggerConstants.PRODUCT_PHOTO_FILE_NAME
import hu.netsurf.erp.exception.NotFoundException
import hu.netsurf.erp.extension.asString
import hu.netsurf.erp.extension.getExtension
import hu.netsurf.erp.extension.logError
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.extension.toPhotoFile
import hu.netsurf.erp.service.ProductPhotoService
import hu.netsurf.erp.wrapper.PhotoFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin
@RequestMapping(path = ["/api/photos/product"])
class ProductPhotoController(
    private val productPhotoService: ProductPhotoService,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping(path = ["/retrieve/{fileName}"])
    fun getProductPhoto(
        @PathVariable fileName: String,
    ): ResponseEntity<*> {
        try {
            logger.logInfo(
                GET_PRODUCT_PHOTO_REQUEST_RECEIVED,
                mapOf(FILE_NAME to fileName),
            )

            val photo = productPhotoService.getProductPhoto(fileName)

            val headers = HttpHeaders()
            headers.contentType = MediaType.parseMediaType("$IMAGE/${fileName.getExtension()}")

            logger.logInfo(
                GET_PRODUCT_PHOTO_SUCCESS_RESPONSE,
                mapOf(
                    FILE_NAME to fileName,
                    CONTENT_TYPE to headers.contentType.toString(),
                ),
            )

            return ResponseEntity(photo, headers, HttpStatus.OK)
        } catch (exception: NotFoundException) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping(path = ["/upload/{productId}"])
    fun createProductPhoto(
        @PathVariable productId: Int,
        @RequestParam("file") file: MultipartFile,
    ): ResponseEntity<String> {
        try {
            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED,
                mapOf(
                    PRODUCT_ID to productId,
                    MULTIPART_FILE to file.asString(),
                ),
            )

            val photoFile: PhotoFile = file.toPhotoFile()

            logger.logInfo(
                MULTIPART_FILE_MAPPED_TO_PHOTO_FILE,
                mapOf(
                    MULTIPART_FILE to file.asString(),
                    PHOTO_FILE to photoFile.asString(),
                ),
            )

            val productPhotoFileName = productPhotoService.uploadProductPhoto(productId, photoFile)

            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE,
                mapOf(
                    PRODUCT_ID to productId,
                    PRODUCT_PHOTO_FILE_NAME to productPhotoFileName.toString(),
                ),
            )

            return ResponseEntity(productPhotoFileName, HttpStatus.OK)
        } catch (exception: NotFoundException) {
            logger.logError(UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            logger.logError(UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }
}

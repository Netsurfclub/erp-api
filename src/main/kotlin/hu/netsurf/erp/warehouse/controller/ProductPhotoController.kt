package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.exception.NotFoundException
import hu.netsurf.erp.common.extension.logError
import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.FileConstants.IMAGE
import hu.netsurf.erp.warehouse.constant.FileConstants.PRODUCT_PHOTO
import hu.netsurf.erp.warehouse.constant.LogEventConstants.GET_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.GET_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.GET_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.MULTIPART_FILE_MAPPED_TO_PHOTO_FILE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.CONTENT_TYPE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.FILE_NAME
import hu.netsurf.erp.warehouse.constant.LoggerConstants.MULTIPART_FILE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PHOTO_FILE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PRODUCT_ID
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PRODUCT_PHOTO_FILE_NAME
import hu.netsurf.erp.warehouse.extension.asString
import hu.netsurf.erp.warehouse.extension.getExtension
import hu.netsurf.erp.warehouse.extension.toPhotoFile
import hu.netsurf.erp.warehouse.service.ProductPhotoService
import hu.netsurf.erp.warehouse.wrapper.PhotoFile
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

            return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .body(photo)
        } catch (exception: NotFoundException) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.message)
        } catch (exception: Exception) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.message)
        }
    }

    @PostMapping(path = ["/upload/{productId}"])
    fun uploadProductPhoto(
        @PathVariable productId: Int,
        @RequestParam("file") file: MultipartFile,
    ): ResponseEntity<*> {
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

            val productPhotoFileName = productPhotoService.uploadProductPhoto(productId, photoFile).toString()

            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE,
                mapOf(
                    PRODUCT_ID to productId,
                    PRODUCT_PHOTO_FILE_NAME to productPhotoFileName,
                ),
            )

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapOf(PRODUCT_PHOTO to productPhotoFileName))
        } catch (exception: NotFoundException) {
            logger.logError(UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)

            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.message)
        } catch (exception: Exception) {
            logger.logError(UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)

            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.message)
        }
    }
}

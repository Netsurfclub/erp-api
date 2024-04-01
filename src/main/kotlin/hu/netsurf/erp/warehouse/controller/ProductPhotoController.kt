package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.GET_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.GET_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.GET_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.CONTENT_TYPE
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.FILE_NAME
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.MULTIPART_FILE
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.PRODUCT_ID
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.PRODUCT_PHOTO_FILE_NAME
import hu.netsurf.erp.common.logging.extension.logError
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.constant.EndpointConstants.CONTROLLER_PATH_PRODUCT_PHOTOS
import hu.netsurf.erp.warehouse.constant.EndpointConstants.PATH_SEGMENT_UPLOAD
import hu.netsurf.erp.warehouse.constant.EndpointConstants.PATH_VARIABLE_FILE_NAME
import hu.netsurf.erp.warehouse.constant.EndpointConstants.PATH_VARIABLE_PRODUCT_ID
import hu.netsurf.erp.warehouse.constant.EndpointConstants.REQUEST_PARAM_FILE
import hu.netsurf.erp.warehouse.exception.NotFoundException
import hu.netsurf.erp.warehouse.extension.asString
import hu.netsurf.erp.warehouse.service.ProductPhotoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping(path = [CONTROLLER_PATH_PRODUCT_PHOTOS])
class ProductPhotoController(private val productPhotoService: ProductPhotoService) {
    val logger: Logger = LoggerFactory.getLogger(ProductPhotoController::class.java)

    @GetMapping(path = [PATH_VARIABLE_FILE_NAME])
    fun getProductPhoto(@PathVariable fileName: String): ResponseEntity<*> {
        try {
            logger.logInfo(
                GET_PRODUCT_PHOTO_REQUEST_RECEIVED,
                mapOf(FILE_NAME to fileName),
            )

            val photo = productPhotoService.getPhoto(fileName)
            val contentType = productPhotoService.getContentType(fileName)

            logger.logInfo(
                GET_PRODUCT_PHOTO_SUCCESS_RESPONSE,
                mapOf(
                    FILE_NAME to fileName,
                    CONTENT_TYPE to contentType,
                ),
            )

            val headers = HttpHeaders()
            headers.contentType = MediaType.parseMediaType(contentType)

            return ResponseEntity(photo, headers, HttpStatus.OK)
        } catch (exception: NotFoundException) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            logger.logError(GET_PRODUCT_PHOTO_FAILURE_RESPONSE, exception)
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping(path = ["$PATH_VARIABLE_PRODUCT_ID/$PATH_SEGMENT_UPLOAD"])
    fun uploadProductPhoto(
        @PathVariable productId: Int,
        @RequestParam(REQUEST_PARAM_FILE) file: MultipartFile,
    ): ResponseEntity<String> {
        try {
            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED,
                mapOf(
                    PRODUCT_ID to productId,
                    MULTIPART_FILE to file.asString(),
                ),
            )

            val productPhotoFileName = productPhotoService.uploadPhoto(productId, file)

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

package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE
import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED
import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants.UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constants.warehouse.LoggerConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.common.logging.constants.warehouse.LoggerConstants.PRODUCT_ID
import hu.netsurf.erp.common.logging.extension.logError
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.constants.EndpointConstants.CONTROLLER_PATH_PRODUCT_PHOTOS
import hu.netsurf.erp.warehouse.constants.EndpointConstants.PATH_VARIABLE_PRODUCT_ID
import hu.netsurf.erp.warehouse.constants.EndpointConstants.REQUEST_PARAM_FILE
import hu.netsurf.erp.warehouse.exception.NotFoundException
import hu.netsurf.erp.warehouse.service.ProductPhotoService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @PostMapping(path = [PATH_VARIABLE_PRODUCT_ID])
    fun uploadProductPhoto(
        @PathVariable productId: Int,
        @RequestParam(REQUEST_PARAM_FILE) file: MultipartFile,
    ): ResponseEntity<String> {
        try {
            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED,
                mapOf(PRODUCT_ID to productId),
            )

            val photoFileName = productPhotoService.uploadPhoto(productId, file)

            logger.logInfo(
                UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE,
                mapOf(
                    PRODUCT_ID to productId,
                    PHOTO_FILE_NAME to photoFileName.toString(),
                ),
            )

            return ResponseEntity(photoFileName, HttpStatus.OK)
        } catch (exception: NotFoundException) {
            logger.logError(
                UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE,
                exception,
            )

            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            logger.logError(
                UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE,
                exception,
            )

            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }
}

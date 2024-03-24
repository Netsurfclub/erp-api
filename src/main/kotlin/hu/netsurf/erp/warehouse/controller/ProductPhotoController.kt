package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.constants.EndpointConstants.CONTROLLER_PATH_PRODUCT_PHOTOS
import hu.netsurf.erp.warehouse.constants.EndpointConstants.PATH_VARIABLE_PRODUCT_ID
import hu.netsurf.erp.warehouse.constants.EndpointConstants.REQUEST_PARAM_FILE
import hu.netsurf.erp.warehouse.exception.NotFoundException
import hu.netsurf.erp.warehouse.service.ProductPhotoService
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

    @PostMapping(path = [PATH_VARIABLE_PRODUCT_ID])
    fun uploadProductPhoto(
        @PathVariable productId: Int,
        @RequestParam(REQUEST_PARAM_FILE) file: MultipartFile,
    ): ResponseEntity<String> {
        try {
            val photoFileName = productPhotoService.uploadPhoto(productId, file)
            return ResponseEntity(photoFileName, HttpStatus.OK)
        } catch (exception: NotFoundException) {
            return ResponseEntity(exception.message, HttpStatus.NOT_FOUND)
        } catch (exception: Exception) {
            return ResponseEntity(exception.message, HttpStatus.BAD_REQUEST)
        }
    }
}

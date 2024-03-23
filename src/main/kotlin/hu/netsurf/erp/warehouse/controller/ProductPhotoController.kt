package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.constants.EndpointConstants.CONTROLLER_PATH_PRODUCT_PHOTOS
import hu.netsurf.erp.warehouse.constants.EndpointConstants.PATH_VARIABLE_PRODUCT_ID
import hu.netsurf.erp.warehouse.constants.EndpointConstants.REQUEST_PARAM_FILE
import hu.netsurf.erp.warehouse.model.ProductPhoto
import hu.netsurf.erp.warehouse.service.ProductPhotoService
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
    ): ProductPhoto {
        return productPhotoService.uploadPhoto(file)
    }
}

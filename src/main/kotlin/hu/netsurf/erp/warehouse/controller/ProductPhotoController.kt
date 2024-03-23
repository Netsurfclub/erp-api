package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.constants.EndpointConstants.CONTROLLER_PATH_PRODUCT_PHOTOS
import hu.netsurf.erp.warehouse.constants.EndpointConstants.PATH_VARIABLE_PRODUCT_ID
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = [CONTROLLER_PATH_PRODUCT_PHOTOS])
class ProductPhotoController {

    @PostMapping(path = [PATH_VARIABLE_PRODUCT_ID])
    fun uploadProductPhoto(@PathVariable productId: Int): Int {
        return productId
    }
}

package hu.netsurf.erp.warehouse.controller

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/api/product/photos"])
class ProductPhotoController {

    @PostMapping(path = ["/{productId}"])
    fun uploadProductPhoto(@PathVariable productId: Int): Int {
        return productId
    }
}

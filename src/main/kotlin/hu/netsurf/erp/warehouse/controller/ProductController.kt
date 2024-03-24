package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {
    val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @QueryMapping(name = "products")
    fun products(): List<Product> {
        return productService.getProducts()
    }
}

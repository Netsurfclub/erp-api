package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.repository.ProductRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productRepository: ProductRepository) {

    @QueryMapping
    fun products(): List<Product> {
        return productRepository.findAll()
    }
}

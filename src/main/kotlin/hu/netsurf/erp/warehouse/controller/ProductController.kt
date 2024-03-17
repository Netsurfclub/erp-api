package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.repository.ProductRepository
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class ProductController(private val productRepository: ProductRepository) {

    @QueryMapping
    fun getProducts(): List<Product> {
        return productRepository.getProducts()
    }
}

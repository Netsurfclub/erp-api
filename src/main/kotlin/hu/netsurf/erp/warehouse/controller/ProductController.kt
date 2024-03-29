package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
import hu.netsurf.erp.warehouse.service.ProductService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    @QueryMapping(name = "products")
    fun products(): List<Product> {
        return productService.getProducts()
    }

    @MutationMapping(name = "createProduct")
    fun createProduct(@Argument input: ProductInput): Product {
        return productService.createProduct(input)
    }
}

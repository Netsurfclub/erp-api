package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
import hu.netsurf.erp.warehouse.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {
    val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @QueryMapping(name = "products")
    fun products(): List<Product> {
        logger.logInfo(PRODUCTS_GRAPHQL_QUERY_RECEIVED)

        return productService.getProducts()
    }

    @MutationMapping(name = "createProduct")
    fun createProduct(@Argument input: ProductInput): Product {
        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED)

        val product = productService.createProduct(input)
        return productService.getProduct(product.id)
    }
}

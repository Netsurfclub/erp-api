package hu.netsurf.erp.controller

import hu.netsurf.erp.constant.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.PRODUCT_INPUT_MAPPED_TO_PRODUCT
import hu.netsurf.erp.constant.LoggerConstants.PRODUCT
import hu.netsurf.erp.constant.LoggerConstants.PRODUCT_INPUT
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.extension.toProduct
import hu.netsurf.erp.model.Product
import hu.netsurf.erp.model.ProductInput
import hu.netsurf.erp.service.ProductService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @QueryMapping(name = "products")
    fun products(): List<Product> {
        logger.logInfo(PRODUCTS_GRAPHQL_QUERY_RECEIVED)

        val products = productService.getProducts()

        logger.logInfo(PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE)

        return products
    }

    @MutationMapping(name = "createProduct")
    fun createProduct(
        @Argument input: ProductInput,
    ): Product {
        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED)

        val product = input.toProduct()

        logger.logInfo(
            PRODUCT_INPUT_MAPPED_TO_PRODUCT,
            mapOf(
                PRODUCT_INPUT to input,
                PRODUCT to product,
            ),
        )

        val createdProduct = productService.createProduct(product)

        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return createdProduct
    }
}

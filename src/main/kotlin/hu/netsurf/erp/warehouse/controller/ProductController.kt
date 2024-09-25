package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.usermanagement.model.CreateProductRequest
import hu.netsurf.erp.usermanagement.model.CreateProductResponse
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.service.ProductService
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
    val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @QueryMapping(name = "products")
    fun products(): List<Product> {
        logger.logInfo(PRODUCTS_GRAPHQL_QUERY_RECEIVED)

        val products = productService.getProducts()

        logger.logInfo(PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE)

        return products
    }

    @MutationMapping(name = "createProduct")
    fun createProduct(
        @Argument request: CreateProductRequest,
    ): CreateProductResponse {
        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED)

        val product = productService.createProduct(request.input)

        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return CreateProductResponse(product)
    }
}

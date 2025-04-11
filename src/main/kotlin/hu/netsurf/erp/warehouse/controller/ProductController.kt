package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PRODUCT_INPUT_MAPPED_TO_PRODUCT
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PRODUCT
import hu.netsurf.erp.warehouse.constant.LoggerConstants.PRODUCT_INPUT
import hu.netsurf.erp.warehouse.input.CreateProductInput
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.service.ProductService
import hu.netsurf.erp.warehouse.util.sanitization.ProductInputSanitizer
import hu.netsurf.erp.warehouse.util.validation.ProductInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
    private val productInputSanitizer: ProductInputSanitizer,
    private val productInputValidator: ProductInputValidator,
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
        @Argument input: CreateProductInput,
    ): Product {
        logger.logInfo(CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedProductInput = productInputSanitizer.sanitize(input)
        productInputValidator.validate(sanitizedProductInput)

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

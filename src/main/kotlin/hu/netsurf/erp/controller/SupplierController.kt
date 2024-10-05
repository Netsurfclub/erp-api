package hu.netsurf.erp.controller

import hu.netsurf.erp.constant.LogEventConstants.CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.CREATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.SUPPLIERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.SUPPLIERS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.SUPPLIER_INPUT_MAPPED_TO_SUPPLIER
import hu.netsurf.erp.constant.LoggerConstants.SUPPLIER
import hu.netsurf.erp.constant.LoggerConstants.SUPPLIER_INPUT
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.extension.toSupplier
import hu.netsurf.erp.input.SupplierInput
import hu.netsurf.erp.model.Supplier
import hu.netsurf.erp.service.SupplierService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SupplierController(
    private val supplierService: SupplierService,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @QueryMapping(name = "suppliers")
    fun suppliers(): List<Supplier> {
        logger.logInfo(SUPPLIERS_GRAPHQL_QUERY_RECEIVED)

        val suppliers = supplierService.getSuppliers()

        logger.logInfo(SUPPLIERS_GRAPHQL_QUERY_SUCCESS_RESPONSE)

        return suppliers
    }

    @MutationMapping(name = "createSupplier")
    fun createSupplier(
        @Argument input: SupplierInput,
    ): Supplier {
        logger.logInfo(CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED)

        val supplier = input.toSupplier()

        logger.logInfo(
            SUPPLIER_INPUT_MAPPED_TO_SUPPLIER,
            mapOf(
                SUPPLIER_INPUT to input,
                SUPPLIER to supplier,
            ),
        )

        val createdSupplier = supplierService.createSupplier(supplier)

        logger.logInfo(CREATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return createdSupplier
    }
}

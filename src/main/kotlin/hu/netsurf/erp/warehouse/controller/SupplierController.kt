package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.LogEventConstants.CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.CREATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIERS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIER_INPUT_MAPPED_TO_SUPPLIER
import hu.netsurf.erp.warehouse.constant.LogEventConstants.UPDATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.warehouse.constant.LogEventConstants.UPDATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.SUPPLIER
import hu.netsurf.erp.warehouse.constant.LoggerConstants.SUPPLIER_INPUT
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.service.SupplierService
import hu.netsurf.erp.warehouse.util.SupplierInputSanitizer
import hu.netsurf.erp.warehouse.util.SupplierInputValidator
import hu.netsurf.erp.warehouse.util.sanitization.UpdateSupplierInputSanitizer
import hu.netsurf.erp.warehouse.util.validation.UpdateSupplierInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SupplierController(
    private val supplierService: SupplierService,
    private val supplierInputSanitizer: SupplierInputSanitizer,
    private val supplierInputValidator: SupplierInputValidator,
    private val updateSupplierInputSanitizer: UpdateSupplierInputSanitizer,
    private val updateSupplierInputValidator: UpdateSupplierInputValidator,
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
        @Argument input: CreateSupplierInput,
    ): Supplier {
        logger.logInfo(CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedSupplierInput = supplierInputSanitizer.sanitize(input)
        supplierInputValidator.validate(sanitizedSupplierInput)

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

    @MutationMapping(name = "updateSupplier")
    fun updateSupplier(
        @Argument input: UpdateSupplierInput,
    ): Supplier {
        logger.logInfo(UPDATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedUpdateSupplierInput = updateSupplierInputSanitizer.sanitize(input)
        updateSupplierInputValidator.validate(sanitizedUpdateSupplierInput)

        val supplier = input.toSupplier()

        logger.logInfo(UPDATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return supplierService.updateSupplier(supplier)
    }
}

package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.SUPPLIERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput
import hu.netsurf.erp.warehouse.service.SupplierService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SupplierController(private val supplierService: SupplierService) {
    val logger: Logger = LoggerFactory.getLogger(ProductController::class.java)

    @QueryMapping(name = "suppliers")
    fun products(): List<Supplier> {
        logger.logInfo(SUPPLIERS_GRAPHQL_QUERY_RECEIVED)

        return supplierService.getSuppliers()
    }

    @MutationMapping(name = "createSupplier")
    fun createSupplier(@Argument input: SupplierInput): Supplier {
        logger.logInfo(CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED)

        return supplierService.createSupplier(input)
    }
}

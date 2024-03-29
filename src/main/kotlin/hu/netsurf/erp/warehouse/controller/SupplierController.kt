package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput
import hu.netsurf.erp.warehouse.service.SupplierService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SupplierController(private val supplierService: SupplierService) {

    @QueryMapping(name = "suppliers")
    fun products(): List<Supplier> {
        return supplierService.getSuppliers()
    }

    @MutationMapping(name = "createSupplier")
    fun createSupplier(@Argument input: SupplierInput): Supplier {
        return supplierService.createSupplier(input)
    }
}

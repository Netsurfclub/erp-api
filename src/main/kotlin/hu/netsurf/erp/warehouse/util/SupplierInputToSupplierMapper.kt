package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput

fun mapToSupplier(supplierInput: SupplierInput): Supplier {
    return Supplier(
        name = supplierInput.name,
        email = supplierInput.email,
        phone = supplierInput.phone,
    )
}

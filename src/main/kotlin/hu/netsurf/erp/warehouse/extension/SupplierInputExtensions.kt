package hu.netsurf.erp.warehouse.extension

import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput

fun SupplierInput.toSupplier(): Supplier {
    return Supplier(
        name = this.name,
        email = this.email,
        phone = this.phone,
    )
}

package hu.netsurf.erp.extension

import hu.netsurf.erp.model.Supplier
import hu.netsurf.erp.model.SupplierInput

fun SupplierInput.toSupplier() =
    Supplier(
        name = this.name,
        email = this.email,
        phone = this.phone,
    )

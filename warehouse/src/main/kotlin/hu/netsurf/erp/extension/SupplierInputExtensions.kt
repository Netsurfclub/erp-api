package hu.netsurf.erp.extension

import hu.netsurf.erp.input.SupplierInput
import hu.netsurf.erp.model.Supplier

fun SupplierInput.toSupplier() =
    Supplier(
        name = this.name,
        email = this.email,
        phone = this.phone,
    )

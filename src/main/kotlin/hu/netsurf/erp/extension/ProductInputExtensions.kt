package hu.netsurf.erp.extension

import hu.netsurf.erp.input.ProductInput
import hu.netsurf.erp.model.Product
import hu.netsurf.erp.model.Supplier

fun ProductInput.toProduct() =
    Product(
        name = this.name,
        supplier = Supplier(id = this.supplierId),
        price = this.price,
        unit = this.unit,
        onStock = this.onStock,
    )

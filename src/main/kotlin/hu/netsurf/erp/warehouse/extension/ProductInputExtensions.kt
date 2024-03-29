package hu.netsurf.erp.warehouse.extension

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
import hu.netsurf.erp.warehouse.model.Supplier

fun ProductInput.toProduct(): Product {
    return Product(
        name = this.name,
        supplier = Supplier(
            id = this.supplierId,
        ),
        price = this.price,
        unit = this.unit,
        onStock = this.onStock,
    )
}

package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
import hu.netsurf.erp.warehouse.model.Supplier

fun mapToProduct(productInput: ProductInput): Product {
    return Product(
        name = productInput.name,
        supplier = Supplier(
            id = productInput.supplierId
        ),
        price = productInput.price,
        unit = productInput.unit,
        onStock = productInput.onStock,
    )
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.ProductInput

class ProductInputTestObject {
    companion object {
        fun productInput1(): ProductInput =
            ProductInput(
                name = "Product#1",
                supplierId = 1,
                price = 10000.0,
                unit = "pieces",
                onStock = 10,
            )
    }
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.ProductInput

class ProductInputTestObject {
    companion object {
        fun productInput1(): ProductInput = build()

        private fun build(
            name: String = "Product#1",
            supplierId: Int = 1,
            price: Double = 10000.0,
            unit: String = "pieces",
            onStock: Int = 10,
        ): ProductInput =
            ProductInput(
                name = name,
                supplierId = supplierId,
                price = price,
                unit = unit,
                onStock = onStock,
            )
    }
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.Supplier

class ProductTestObject {
    companion object {
        fun product1(): Product = build()

        fun product1WithPhoto(): Product = build(photo = "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg")

        fun product2(): Product =
            build(
                id = 2,
                name = "Product#2",
                price = 20000.0,
                onStock = 20,
            )

        private fun build(
            id: Int = 1,
            name: String = "Product#1",
            supplier: Supplier = SupplierTestObject.supplier1(),
            price: Double = 10000.0,
            unit: String = "pieces",
            photo: String? = null,
            onStock: Int = 10,
        ): Product =
            Product(
                id = id,
                name = name,
                supplier = supplier,
                price = price,
                unit = unit,
                photo = photo,
                onStock = onStock,
            )
    }
}

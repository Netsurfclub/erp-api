package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.Product

class ProductTestObject {
    companion object {
        fun product1(): Product =
            Product(
                id = 1,
                name = "Product#1",
                supplier = SupplierTestObject.supplier1(),
                price = 10000.0,
                unit = "pieces",
                photo = null,
                onStock = 10,
            )

        fun product2(): Product =
            Product(
                id = 2,
                name = "Product#2",
                supplier = SupplierTestObject.supplier1(),
                price = 20000.0,
                unit = "pieces",
                photo = null,
                onStock = 20,
            )
    }
}

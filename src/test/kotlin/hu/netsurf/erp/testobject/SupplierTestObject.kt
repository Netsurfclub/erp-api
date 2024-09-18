package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.Supplier

class SupplierTestObject {
    companion object {
        fun supplier1(): Supplier =
            Supplier(
                id = 1,
                name = "Supplier#1",
                phone = "555123",
                email = "supplier1@test.com",
            )
    }
}

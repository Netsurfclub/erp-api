package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.Supplier

class SupplierTestObject {
    companion object {
        fun supplier1(): Supplier = build()

        fun supplier2(): Supplier =
            build(
                id = 2,
                name = "Supplier#2",
                phone = "555456",
                email = "supplier2@test.com",
            )

        private fun build(
            id: Int = 1,
            name: String = "Supplier#1",
            phone: String = "555123",
            email: String = "supplier1@test.com",
        ): Supplier =
            Supplier(
                id = id,
                name = name,
                phone = phone,
                email = email,
            )
    }
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.SupplierInput

class SupplierInputTestObject {
    companion object {
        fun supplierInput1(): SupplierInput = build()

        private fun build(
            name: String = "Supplier#1",
            phone: String = "555123",
            email: String = "supplier1@test.com",
        ): SupplierInput =
            SupplierInput(
                name = name,
                phone = phone,
                email = email,
            )
    }
}

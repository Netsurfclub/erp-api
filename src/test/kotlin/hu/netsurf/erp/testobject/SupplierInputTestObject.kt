package hu.netsurf.erp.testobject

import hu.netsurf.erp.warehouse.model.SupplierInput

class SupplierInputTestObject {
    companion object {
        fun supplierInput1(): SupplierInput =
            SupplierInput(
                name = "Supplier#1",
                phone = "555123",
                email = "supplier1@test.com",
            )
    }
}

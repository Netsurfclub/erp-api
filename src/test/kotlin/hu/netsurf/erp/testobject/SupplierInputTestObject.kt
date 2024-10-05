package hu.netsurf.erp.testobject

import hu.netsurf.erp.TestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.TestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.TestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.input.SupplierInput

class SupplierInputTestObject {
    companion object {
        fun supplierInput1(): SupplierInput = build()

        private fun build(
            name: String = SUPPLIER_1_NAME,
            phone: String = SUPPLIER_1_PHONE,
            email: String = SUPPLIER_1_EMAIL,
        ): SupplierInput =
            SupplierInput(
                name = name,
                phone = phone,
                email = email,
            )
    }
}

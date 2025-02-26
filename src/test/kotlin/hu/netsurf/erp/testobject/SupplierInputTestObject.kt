package hu.netsurf.erp.testobject

import hu.netsurf.erp.TestConstants.EMPTY_STRING
import hu.netsurf.erp.TestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.TestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.TestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.TestConstants.SUPPLIER_INVALID_EMAIL
import hu.netsurf.erp.TestConstants.SUPPLIER_LONG_EMAIL
import hu.netsurf.erp.TestConstants.SUPPLIER_LONG_NAME
import hu.netsurf.erp.TestConstants.SUPPLIER_LONG_PHONE
import hu.netsurf.erp.input.SupplierInput

class SupplierInputTestObject {
    companion object {
        fun supplierInput1(): SupplierInput = build()

        fun supplierInput1WithEmptyName(): SupplierInput = build(name = EMPTY_STRING)

        fun supplierInput1WithShortName(): SupplierInput = build(name = "a")

        fun supplierInput1WithLongName(): SupplierInput = build(name = SUPPLIER_LONG_NAME)

        fun supplierInput1WithShortPhone(): SupplierInput = build(phone = "1")

        fun supplierInput1WithLongPhone(): SupplierInput = build(phone = SUPPLIER_LONG_PHONE)

        fun supplierInput1WithShortEmail(): SupplierInput = build(email = "a")

        fun supplierInput1WithLongEmail(): SupplierInput = build(email = SUPPLIER_LONG_EMAIL)

        fun supplierInput1WithInvalidEmail(): SupplierInput = build(email = SUPPLIER_INVALID_EMAIL)

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

package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_INVALID_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_PHONE
import hu.netsurf.erp.warehouse.input.SupplierInput

class SupplierInputTestObject {
    companion object {
        fun supplierInput1(): SupplierInput = build()

        fun supplierInput1WithEmptyName(): SupplierInput = build(name = EMPTY_STRING)

        fun supplierInput1WithShortName(): SupplierInput = build(name = "a")

        fun supplierInput1WithLongName(): SupplierInput = build(name = SUPPLIER_LONG_NAME)

        fun supplierInput1WithShortPhone(): SupplierInput = build(phone = "1")

        fun supplierInput1WithLongPhone(): SupplierInput = build(phone = SUPPLIER_LONG_PHONE)

        fun supplierInput1WithNullPhone(): SupplierInput = build(phone = null)

        fun supplierInput1WithShortEmail(): SupplierInput = build(email = "a")

        fun supplierInput1WithLongEmail(): SupplierInput = build(email = SUPPLIER_LONG_EMAIL)

        fun supplierInput1WithInvalidEmail(): SupplierInput = build(email = SUPPLIER_INVALID_EMAIL)

        fun supplierInput1WithNullEmail(): SupplierInput = build(email = null)

        private fun build(
            name: String = SUPPLIER_1_NAME,
            phone: String? = SUPPLIER_1_PHONE,
            email: String? = SUPPLIER_1_EMAIL,
        ): SupplierInput =
            SupplierInput(
                name = name,
                phone = phone,
                email = email,
            )
    }
}

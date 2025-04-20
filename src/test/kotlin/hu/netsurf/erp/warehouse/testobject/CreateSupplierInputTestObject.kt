package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_INVALID_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_LONG_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_LONG_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_LONG_PHONE
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.input.CreateSupplierInput

class CreateSupplierInputTestObject {
    companion object {
        fun input1(): CreateSupplierInput = build()

        fun input1WithEmptyName(): CreateSupplierInput = build(name = EMPTY_STRING)

        fun input1WithShortName(): CreateSupplierInput = build(name = "a")

        fun input1WithLongName(): CreateSupplierInput = build(name = SUPPLIER_1_LONG_NAME)

        fun input1WithShortPhone(): CreateSupplierInput = build(phone = "1")

        fun input1WithLongPhone(): CreateSupplierInput = build(phone = SUPPLIER_1_LONG_PHONE)

        fun input1WithNullPhone(): CreateSupplierInput = build(phone = null)

        fun input1WithShortEmail(): CreateSupplierInput = build(email = "a")

        fun input1WithLongEmail(): CreateSupplierInput = build(email = SUPPLIER_1_LONG_EMAIL)

        fun input1WithInvalidEmail(): CreateSupplierInput = build(email = SUPPLIER_1_INVALID_EMAIL)

        fun input1WithNullEmail(): CreateSupplierInput = build(email = null)

        private fun build(
            name: String = SUPPLIER_1_NAME,
            phone: String? = SUPPLIER_1_PHONE,
            email: String? = SUPPLIER_1_EMAIL,
        ): CreateSupplierInput =
            CreateSupplierInput(
                name = name,
                phone = phone,
                email = email,
            )
    }
}

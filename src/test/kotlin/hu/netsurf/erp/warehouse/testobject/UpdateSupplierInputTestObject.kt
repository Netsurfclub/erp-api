package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_INVALID_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_LONG_PHONE
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput

class UpdateSupplierInputTestObject {
    companion object {
        fun input1(): UpdateSupplierInput = build()

        fun input1WithShortName(): UpdateSupplierInput = build(name = "a")

        fun input1WithLongName(): UpdateSupplierInput = build(name = SUPPLIER_LONG_NAME)

        fun input1WithNullName(): UpdateSupplierInput = build(name = null)

        fun input1WithShortPhone(): UpdateSupplierInput = build(phone = "1")

        fun input1WithLongPhone(): UpdateSupplierInput = build(phone = SUPPLIER_LONG_PHONE)

        fun input1WithNullPhone(): UpdateSupplierInput = build(phone = null)

        fun input1WithShortEmail(): UpdateSupplierInput = build(email = "a")

        fun input1WithLongEmail(): UpdateSupplierInput = build(email = SUPPLIER_LONG_EMAIL)

        fun input1WithInvalidEmail(): UpdateSupplierInput = build(email = SUPPLIER_INVALID_EMAIL)

        fun input1WithNullEmail(): UpdateSupplierInput = build(email = null)

        private fun build(
            name: String? = SUPPLIER_1_NAME,
            phone: String? = SUPPLIER_1_PHONE,
            email: String? = SUPPLIER_1_EMAIL,
        ): UpdateSupplierInput =
            UpdateSupplierInput(
                name = name,
                phone = phone,
                email = email,
            )
    }
}

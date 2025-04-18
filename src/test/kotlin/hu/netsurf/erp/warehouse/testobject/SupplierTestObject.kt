package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_2_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_2_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_2_PHONE
import hu.netsurf.erp.warehouse.model.Supplier

class SupplierTestObject {
    companion object {
        fun supplier1(): Supplier = build()

        fun supplier1WithNullPhone(): Supplier = build(phone = null)

        fun supplier1WithNullEmail(): Supplier = build(email = null)

        fun supplier2(): Supplier =
            build(
                id = 2,
                name = SUPPLIER_2_NAME,
                phone = SUPPLIER_2_PHONE,
                email = SUPPLIER_2_EMAIL,
            )

        private fun build(
            id: Int = 1,
            name: String = SUPPLIER_1_NAME,
            phone: String? = SUPPLIER_1_PHONE,
            email: String? = SUPPLIER_1_EMAIL,
        ): Supplier =
            Supplier(
                id = id,
                name = name,
                phone = phone,
                email = email,
            )
    }
}

package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_UNIT
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_2_NAME
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1

class ProductTestObject {
    companion object {
        fun product1(): Product = build()

        fun product1WithPhoto(): Product = build(photo = PHOTO_FILE_NAME)

        fun product2(): Product =
            build(
                id = 2,
                name = PRODUCT_2_NAME,
                price = 20000.0,
                onStock = 20,
            )

        private fun build(
            id: Int = 1,
            name: String = PRODUCT_1_NAME,
            supplier: Supplier = supplier1(),
            price: Double = 10000.0,
            unit: String = PRODUCT_1_UNIT,
            photo: String? = null,
            onStock: Int = 10,
        ): Product =
            Product(
                id = id,
                name = name,
                supplier = supplier,
                price = price,
                unit = unit,
                photo = photo,
                onStock = onStock,
            )
    }
}

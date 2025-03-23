package hu.netsurf.erp.testobject

import hu.netsurf.erp.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.ProductTestConstants.PRODUCT_1_NAME
import hu.netsurf.erp.ProductTestConstants.PRODUCT_1_UNIT
import hu.netsurf.erp.ProductTestConstants.PRODUCT_LONG_NAME
import hu.netsurf.erp.ProductTestConstants.PRODUCT_LONG_UNIT
import hu.netsurf.erp.input.ProductInput

class ProductInputTestObject {
    companion object {
        fun productInput1(): ProductInput = build()

        fun productInput1WithEmptyName(): ProductInput = build(name = EMPTY_STRING)

        fun productInput1WithShortName(): ProductInput = build(name = "a")

        fun productInput1WithLongName(): ProductInput = build(name = PRODUCT_LONG_NAME)

        fun productInput1WithEmptyUnit(): ProductInput = build(unit = EMPTY_STRING)

        fun productInput1WithShortUnit(): ProductInput = build(unit = "a")

        fun productInput1WithLongUnit(): ProductInput = build(unit = PRODUCT_LONG_UNIT)

        private fun build(
            name: String = PRODUCT_1_NAME,
            supplierId: Int = 1,
            price: Double = 10000.0,
            unit: String = PRODUCT_1_UNIT,
            onStock: Int = 10,
        ): ProductInput =
            ProductInput(
                name = name,
                supplierId = supplierId,
                price = price,
                unit = unit,
                onStock = onStock,
            )
    }
}

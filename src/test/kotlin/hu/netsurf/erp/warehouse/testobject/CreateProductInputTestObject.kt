package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_LONG_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_LONG_UNIT
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_UNIT
import hu.netsurf.erp.warehouse.input.CreateProductInput

class CreateProductInputTestObject {
    companion object {
        fun input1(): CreateProductInput = build()

        fun input1WithEmptyName(): CreateProductInput = build(name = EMPTY_STRING)

        fun input1WithShortName(): CreateProductInput = build(name = "a")

        fun input1WithLongName(): CreateProductInput = build(name = PRODUCT_1_LONG_NAME)

        fun input1WithEmptyUnit(): CreateProductInput = build(unit = EMPTY_STRING)

        fun input1WithShortUnit(): CreateProductInput = build(unit = "a")

        fun input1WithLongUnit(): CreateProductInput = build(unit = PRODUCT_1_LONG_UNIT)

        private fun build(
            name: String = PRODUCT_1_NAME,
            supplierId: Int = 1,
            price: Double = 10000.0,
            unit: String = PRODUCT_1_UNIT,
            onStock: Int = 10,
        ): CreateProductInput =
            CreateProductInput(
                name = name,
                supplierId = supplierId,
                price = price,
                unit = unit,
                onStock = onStock,
            )
    }
}

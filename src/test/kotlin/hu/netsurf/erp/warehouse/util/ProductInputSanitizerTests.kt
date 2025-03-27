package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_UNIT
import hu.netsurf.erp.warehouse.input.ProductInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProductInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val productInputSanitizer: ProductInputSanitizer = ProductInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun productInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    PRODUCT_1_NAME,
                    PRODUCT_1_UNIT,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $PRODUCT_1_NAME",
                    " $PRODUCT_1_UNIT",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$PRODUCT_1_NAME ",
                    "$PRODUCT_1_UNIT ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $PRODUCT_1_NAME ",
                    " $PRODUCT_1_UNIT ",
                ),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productInputParams")
    fun `sanitize tests`(
        testCase: String,
        name: String,
        unit: String,
    ) {
        val productInput =
            ProductInput(
                name = name,
                supplierId = 1,
                price = 100.0,
                unit = unit,
                onStock = 1,
            )

        val result = productInputSanitizer.sanitize(productInput)

        assertEquals(PRODUCT_1_NAME, result.name)
        assertEquals(PRODUCT_1_UNIT, result.unit)
    }
}

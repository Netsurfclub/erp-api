package hu.netsurf.erp.warehouse.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_NAME
import hu.netsurf.erp.warehouse.constant.ProductTestConstants.PRODUCT_1_UNIT
import hu.netsurf.erp.warehouse.input.CreateProductInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal
import java.util.stream.Stream

class CreateProductInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val createSupplierInputSanitizer: CreateProductInputSanitizer = CreateProductInputSanitizer(inputSanitizer)

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
        val input =
            CreateProductInput(
                name = name,
                supplierId = 1,
                price = BigDecimal(100.00),
                unit = unit,
                onStock = 1,
            )

        val result = createSupplierInputSanitizer.sanitize(input)

        assertEquals(PRODUCT_1_NAME, result.name)
        assertEquals(PRODUCT_1_UNIT, result.unit)
    }
}

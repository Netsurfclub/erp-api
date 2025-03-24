package hu.netsurf.erp.util

import hu.netsurf.erp.exception.EmptyFieldException
import hu.netsurf.erp.exception.InvalidLengthException
import hu.netsurf.erp.input.ProductInput
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithEmptyName
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithEmptyUnit
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithLongName
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithLongUnit
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithShortName
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1WithShortUnit
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProductInputValidatorTests {
    private val productInputValidator: ProductInputValidator = ProductInputValidator()

    companion object {
        @JvmStatic
        fun productInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is empty", productInput1WithEmptyName()),
                Arguments.of("unit is empty", productInput1WithEmptyUnit()),
            )

        @JvmStatic
        fun productInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is too short", productInput1WithShortName()),
                Arguments.of("name is too long", productInput1WithLongName()),
                Arguments.of("unit is too short", productInput1WithShortUnit()),
                Arguments.of("unit is too long", productInput1WithLongUnit()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            productInputValidator.validate(productInput1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        productInput: ProductInput,
    ) {
        assertThrows<EmptyFieldException> {
            productInputValidator.validate(productInput)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        productInput: ProductInput,
    ) {
        assertThrows<InvalidLengthException> {
            productInputValidator.validate(productInput)
        }
    }
}

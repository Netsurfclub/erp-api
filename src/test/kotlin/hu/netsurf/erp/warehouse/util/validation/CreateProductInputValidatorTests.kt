package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.CreateProductInput
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithEmptyName
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithEmptyUnit
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithLongName
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithLongUnit
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithShortName
import hu.netsurf.erp.warehouse.testobject.CreateProductInputTestObject.Companion.input1WithShortUnit
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CreateProductInputValidatorTests {
    private val createProductInputValidator: CreateProductInputValidator = CreateProductInputValidator()

    companion object {
        @JvmStatic
        fun productInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is empty", input1WithEmptyName()),
                Arguments.of("unit is empty", input1WithEmptyUnit()),
            )

        @JvmStatic
        fun productInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is too short", input1WithShortName()),
                Arguments.of("name is too long", input1WithLongName()),
                Arguments.of("unit is too short", input1WithShortUnit()),
                Arguments.of("unit is too long", input1WithLongUnit()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            createProductInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        input: CreateProductInput,
    ) {
        assertThrows<EmptyFieldException> {
            createProductInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("productInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        input: CreateProductInput,
    ) {
        assertThrows<InvalidLengthException> {
            createProductInputValidator.validate(input)
        }
    }
}

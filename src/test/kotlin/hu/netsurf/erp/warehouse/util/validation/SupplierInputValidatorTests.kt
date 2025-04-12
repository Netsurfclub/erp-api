package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithEmptyName
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithInvalidEmail
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithLongEmail
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithLongName
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithLongPhone
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithShortEmail
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithShortName
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithShortPhone
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SupplierInputValidatorTests {
    private val supplierInputValidator: SupplierInputValidator = SupplierInputValidator()

    companion object {
        @JvmStatic
        fun supplierInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is empty", input1WithEmptyName()),
            )

        @JvmStatic
        fun supplierInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is too short", input1WithShortName()),
                Arguments.of("name is too long", input1WithLongName()),
                Arguments.of("phone is too short", input1WithShortPhone()),
                Arguments.of("phone is too long", input1WithLongPhone()),
                Arguments.of("email is too short", input1WithShortEmail()),
                Arguments.of("email is too long", input1WithLongEmail()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            supplierInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("supplierInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        input: CreateSupplierInput,
    ) {
        assertThrows<EmptyFieldException> {
            supplierInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("supplierInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        input: CreateSupplierInput,
    ) {
        assertThrows<InvalidLengthException> {
            supplierInputValidator.validate(input)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            supplierInputValidator.validate(input1WithInvalidEmail())
        }
    }
}

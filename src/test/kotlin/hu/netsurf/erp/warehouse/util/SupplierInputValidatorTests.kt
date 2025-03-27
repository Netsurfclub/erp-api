package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.warehouse.input.SupplierInput
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithEmptyName
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithInvalidEmail
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithLongEmail
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithLongName
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithLongPhone
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithShortEmail
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithShortName
import hu.netsurf.erp.warehouse.testobject.SupplierInputTestObject.Companion.supplierInput1WithShortPhone
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
                Arguments.of("name is empty", supplierInput1WithEmptyName()),
            )

        @JvmStatic
        fun supplierInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("name is too short", supplierInput1WithShortName()),
                Arguments.of("name is too long", supplierInput1WithLongName()),
                Arguments.of("phone is too short", supplierInput1WithShortPhone()),
                Arguments.of("phone is too long", supplierInput1WithLongPhone()),
                Arguments.of("email is too short", supplierInput1WithShortEmail()),
                Arguments.of("email is too long", supplierInput1WithLongEmail()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            supplierInputValidator.validate(supplierInput1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("supplierInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        supplierInput: SupplierInput,
    ) {
        assertThrows<EmptyFieldException> {
            supplierInputValidator.validate(supplierInput)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("supplierInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        supplierInput: SupplierInput,
    ) {
        assertThrows<InvalidLengthException> {
            supplierInputValidator.validate(supplierInput)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            supplierInputValidator.validate(supplierInput1WithInvalidEmail())
        }
    }
}

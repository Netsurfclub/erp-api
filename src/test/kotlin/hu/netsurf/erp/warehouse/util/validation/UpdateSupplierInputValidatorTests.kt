package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithInvalidEmail
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithLongEmail
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithLongName
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithLongPhone
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithShortEmail
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithShortName
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithShortPhone
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateSupplierInputValidatorTests {
    private val updateSupplierInputValidator: UpdateSupplierInputValidator = UpdateSupplierInputValidator()

    companion object {
        @JvmStatic
        fun updateSupplierInputFieldLengthParams(): Stream<Arguments> =
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
            updateSupplierInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateSupplierInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        input: UpdateSupplierInput,
    ) {
        assertThrows<InvalidLengthException> {
            updateSupplierInputValidator.validate(input)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            updateSupplierInputValidator.validate(input1WithInvalidEmail())
        }
    }
}

package hu.netsurf.erp.warehouse.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithNullEmail
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithNullName
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1WithNullPhone
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateSupplierInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val updateSupplierInputSanitizer: UpdateSupplierInputSanitizer =
        UpdateSupplierInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun updateSupplierInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    SUPPLIER_1_NAME,
                    SUPPLIER_1_PHONE,
                    SUPPLIER_1_EMAIL,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $SUPPLIER_1_NAME",
                    " $SUPPLIER_1_PHONE",
                    " $SUPPLIER_1_EMAIL",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$SUPPLIER_1_NAME ",
                    "$SUPPLIER_1_PHONE ",
                    "$SUPPLIER_1_EMAIL ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $SUPPLIER_1_NAME ",
                    " $SUPPLIER_1_PHONE ",
                    " $SUPPLIER_1_EMAIL ",
                ),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateSupplierInputParams")
    fun `sanitize tests`(
        testCase: String,
        name: String?,
        phone: String?,
        email: String?,
    ) {
        val input =
            UpdateSupplierInput(
                name = name,
                phone = phone,
                email = email,
            )

        val result = updateSupplierInputSanitizer.sanitize(input)

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - name is null`() {
        val result = updateSupplierInputSanitizer.sanitize(input1WithNullName())

        assertNull(result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - phone is null`() {
        val result = updateSupplierInputSanitizer.sanitize(input1WithNullPhone())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertNull(result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - email is null`() {
        val result = updateSupplierInputSanitizer.sanitize(input1WithNullEmail())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertNull(result.email)
    }
}

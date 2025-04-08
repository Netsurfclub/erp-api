package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.warehouse.constant.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithNullEmail
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithNullPhone
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SupplierInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val supplierInputSanitizer: SupplierInputSanitizer = SupplierInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun supplierInputParams(): Stream<Arguments> =
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
    @MethodSource("supplierInputParams")
    fun `sanitize tests`(
        testCase: String,
        name: String,
        phone: String?,
        email: String?,
    ) {
        val input =
            CreateSupplierInput(
                name = name,
                phone = phone,
                email = email,
            )

        val result = supplierInputSanitizer.sanitize(input)

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - phone is null`() {
        val result = supplierInputSanitizer.sanitize(input1WithNullPhone())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertNull(result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - email is null`() {
        val result = supplierInputSanitizer.sanitize(input1WithNullEmail())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertNull(result.email)
    }
}

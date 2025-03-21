package hu.netsurf.erp.util

import hu.netsurf.erp.SupplierTestConstants.SUPPLIER_1_EMAIL
import hu.netsurf.erp.SupplierTestConstants.SUPPLIER_1_NAME
import hu.netsurf.erp.SupplierTestConstants.SUPPLIER_1_PHONE
import hu.netsurf.erp.UserTestConstants.EMAIL_INPUT
import hu.netsurf.erp.UserTestConstants.NAME_INPUT
import hu.netsurf.erp.UserTestConstants.PHONE_INPUT
import hu.netsurf.erp.input.SupplierInput
import hu.netsurf.erp.testobject.SupplierInputTestObject.Companion.supplierInput1WithNullEmail
import hu.netsurf.erp.testobject.SupplierInputTestObject.Companion.supplierInput1WithNullPhone
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
                    NAME_INPUT,
                    PHONE_INPUT,
                    EMAIL_INPUT,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $NAME_INPUT",
                    " $PHONE_INPUT",
                    " $EMAIL_INPUT",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$NAME_INPUT ",
                    "$PHONE_INPUT ",
                    "$EMAIL_INPUT ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $NAME_INPUT ",
                    " $PHONE_INPUT ",
                    " $EMAIL_INPUT ",
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
        val supplierInput =
            SupplierInput(
                name = name,
                phone = phone,
                email = email,
            )

        val result = supplierInputSanitizer.sanitize(supplierInput)

        assertEquals(NAME_INPUT, result.name)
        assertEquals(PHONE_INPUT, result.phone)
        assertEquals(EMAIL_INPUT, result.email)
    }

    @Test
    fun `sanitize test - phone is null`() {
        val result = supplierInputSanitizer.sanitize(supplierInput1WithNullPhone())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertNull(result.phone)
        assertEquals(SUPPLIER_1_EMAIL, result.email)
    }

    @Test
    fun `sanitize test - email is null`() {
        val result = supplierInputSanitizer.sanitize(supplierInput1WithNullEmail())

        assertEquals(SUPPLIER_1_NAME, result.name)
        assertEquals(SUPPLIER_1_PHONE, result.phone)
        assertNull(result.email)
    }
}

package hu.netsurf.erp.util

import hu.netsurf.erp.TestConstants.EMAIL_INPUT
import hu.netsurf.erp.TestConstants.NAME_INPUT
import hu.netsurf.erp.TestConstants.PHONE_INPUT
import hu.netsurf.erp.input.SupplierInput
import org.junit.jupiter.api.Assertions.assertEquals
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
                    EMAIL_INPUT,
                    PHONE_INPUT
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $NAME_INPUT",
                    " $EMAIL_INPUT",
                    " $PHONE_INPUT",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$NAME_INPUT ",
                    "$EMAIL_INPUT ",
                    "$PHONE_INPUT ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $NAME_INPUT ",
                    " $EMAIL_INPUT ",
                    " $PHONE_INPUT ",
                ),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("supplierInputParams")
    fun `sanitize tests`(
        testCase: String,
        name: String,
        email: String,
        phone: String,
    ) {
        val supplierInput =
            SupplierInput(
                name = name,
                email = email,
                phone = phone,
            )

        val result = supplierInputSanitizer.sanitize(supplierInput)

        assertEquals(NAME_INPUT, result.name)
        assertEquals(EMAIL_INPUT, result.email)
        assertEquals(PHONE_INPUT, result.phone)
    }
}

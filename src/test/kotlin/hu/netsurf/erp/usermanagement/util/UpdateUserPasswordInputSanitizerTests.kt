package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateUserPasswordInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val updateUserPasswordInputSanitizer: UpdateUserPasswordInputSanitizer = UpdateUserPasswordInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun updateUserPasswordInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("no whitespace", "pAsSwOrD", "NeWpAsSwOrD", "NeWpAsSwOrD"),
                Arguments.of("one whitespace on left side", " pAsSwOrD", " NeWpAsSwOrD", " NeWpAsSwOrD"),
                Arguments.of("one whitespace on right side", "pAsSwOrD ", "NeWpAsSwOrD ", "NeWpAsSwOrD "),
                Arguments.of("whitespaces on both right and left side", " pAsSwOrD ", " NeWpAsSwOrD ", " NeWpAsSwOrD "),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateUserPasswordInputParams")
    fun `sanitize tests`(
        testCase: String,
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String,
    ) {
        val updateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = 1,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )

        val result = updateUserPasswordInputSanitizer.sanitize(updateUserPasswordInput)

        assertEquals("pAsSwOrD", result.currentPassword)
        assertEquals("NeWpAsSwOrD", result.newPassword)
        assertEquals("NeWpAsSwOrD", result.confirmNewPassword)
    }
}

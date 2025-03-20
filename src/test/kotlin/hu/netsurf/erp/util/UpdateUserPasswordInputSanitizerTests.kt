package hu.netsurf.erp.util

import hu.netsurf.erp.UserTestConstants.NEW_PASSWORD
import hu.netsurf.erp.UserTestConstants.PASSWORD
import hu.netsurf.erp.input.UpdateUserPasswordInput
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
                Arguments.of("no whitespace", PASSWORD, NEW_PASSWORD, NEW_PASSWORD),
                Arguments.of("one whitespace on left side", " $PASSWORD", " $NEW_PASSWORD", " $NEW_PASSWORD"),
                Arguments.of("one whitespace on right side", "$PASSWORD ", "$NEW_PASSWORD ", "$NEW_PASSWORD "),
                Arguments.of("whitespaces on both right and left side", " $PASSWORD ", " $NEW_PASSWORD ", " $NEW_PASSWORD "),
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

        assertEquals(PASSWORD, result.currentPassword)
        assertEquals(NEW_PASSWORD, result.newPassword)
        assertEquals(NEW_PASSWORD, result.confirmNewPassword)
    }
}

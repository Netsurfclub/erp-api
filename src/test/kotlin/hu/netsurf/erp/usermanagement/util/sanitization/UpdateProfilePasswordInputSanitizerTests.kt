package hu.netsurf.erp.usermanagement.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateProfilePasswordInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val updateProfilePasswordInputSanitizer: UpdateProfilePasswordInputSanitizer =
        UpdateProfilePasswordInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun updateUserPasswordInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    PROFILE_1_PASSWORD,
                    PROFILE_1_NEW_PASSWORD,
                    PROFILE_1_NEW_PASSWORD,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $PROFILE_1_PASSWORD",
                    " $PROFILE_1_NEW_PASSWORD",
                    " $PROFILE_1_NEW_PASSWORD",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$PROFILE_1_PASSWORD ",
                    "$PROFILE_1_NEW_PASSWORD ",
                    "$PROFILE_1_NEW_PASSWORD ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $PROFILE_1_PASSWORD ",
                    " $PROFILE_1_NEW_PASSWORD ",
                    " $PROFILE_1_NEW_PASSWORD ",
                ),
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
        val input =
            UpdateProfilePasswordInput(
                id = 1,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )

        val result = updateProfilePasswordInputSanitizer.sanitize(input)

        assertEquals(PROFILE_1_PASSWORD, result.currentPassword)
        assertEquals(PROFILE_1_NEW_PASSWORD, result.newPassword)
        assertEquals(PROFILE_1_NEW_PASSWORD, result.confirmNewPassword)
    }
}

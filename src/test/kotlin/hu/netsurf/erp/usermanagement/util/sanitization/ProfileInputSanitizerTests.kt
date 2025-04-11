package hu.netsurf.erp.usermanagement.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ProfileInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val profileInputSanitizer: ProfileInputSanitizer = ProfileInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun userInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    USERNAME_1,
                    PASSWORD,
                    PASSWORD,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $USERNAME_1",
                    " $PASSWORD",
                    " $PASSWORD",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$USERNAME_1 ",
                    "$PASSWORD ",
                    "$PASSWORD ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $USERNAME_1 ",
                    " $PASSWORD ",
                    " $PASSWORD ",
                ),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputParams")
    fun `sanitize tests`(
        testCase: String,
        username: String,
        password: String,
        confirmPassword: String,
    ) {
        val input =
            CreateProfileInput(
                userId = 1,
                username = username,
                password = password,
                confirmPassword = confirmPassword,
            )

        val result = profileInputSanitizer.sanitize(input)

        assertEquals(1, result.userId)
        assertEquals(USERNAME_1, result.username)
        assertEquals(PASSWORD, result.password)
        assertEquals(PASSWORD, result.confirmPassword)
    }
}

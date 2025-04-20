package hu.netsurf.erp.usermanagement.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_USERNAME
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CreateProfileInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val createProfileInputSanitizer: CreateProfileInputSanitizer = CreateProfileInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun userInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    PROFILE_1_USERNAME,
                    PROFILE_1_PASSWORD,
                    PROFILE_1_PASSWORD,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $PROFILE_1_USERNAME",
                    " $PROFILE_1_PASSWORD",
                    " $PROFILE_1_PASSWORD",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$PROFILE_1_USERNAME ",
                    "$PROFILE_1_PASSWORD ",
                    "$PROFILE_1_PASSWORD ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $PROFILE_1_USERNAME ",
                    " $PROFILE_1_PASSWORD ",
                    " $PROFILE_1_PASSWORD ",
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

        val result = createProfileInputSanitizer.sanitize(input)

        assertEquals(1, result.userId)
        assertEquals(PROFILE_1_USERNAME, result.username)
        assertEquals(PROFILE_1_PASSWORD, result.password)
        assertEquals(PROFILE_1_PASSWORD, result.confirmPassword)
    }
}

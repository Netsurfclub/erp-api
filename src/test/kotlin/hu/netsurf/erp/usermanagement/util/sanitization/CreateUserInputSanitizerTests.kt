package hu.netsurf.erp.usermanagement.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_EMAIL
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_FIRST_NAME
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LAST_NAME
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CreateUserInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val createUserInputSanitizer: CreateUserInputSanitizer = CreateUserInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun userInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    USER_1_FIRST_NAME,
                    USER_1_LAST_NAME,
                    USER_1_EMAIL,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $USER_1_FIRST_NAME",
                    " $USER_1_LAST_NAME",
                    " $USER_1_EMAIL",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$USER_1_FIRST_NAME ",
                    "$USER_1_LAST_NAME ",
                    "$USER_1_EMAIL ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $USER_1_FIRST_NAME ",
                    " $USER_1_LAST_NAME ",
                    " $USER_1_EMAIL ",
                ),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputParams")
    fun `sanitize tests`(
        testCase: String,
        firstName: String,
        lastName: String,
        email: String,
    ) {
        val input =
            CreateUserInput(
                firstName = firstName,
                lastName = lastName,
                email = email,
            )

        val result = createUserInputSanitizer.sanitize(input)

        assertEquals(USER_1_FIRST_NAME, result.firstName)
        assertEquals(USER_1_LAST_NAME, result.lastName)
        assertEquals(USER_1_EMAIL, result.email)
    }
}

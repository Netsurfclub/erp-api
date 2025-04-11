package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UserInputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()
    private val userInputSanitizer: UserInputSanitizer = UserInputSanitizer(inputSanitizer)

    companion object {
        @JvmStatic
        fun userInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "no whitespace",
                    FIRST_NAME_1,
                    LAST_NAME_1,
                    EMAIL_1,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $FIRST_NAME_1",
                    " $LAST_NAME_1",
                    " $EMAIL_1",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$FIRST_NAME_1 ",
                    "$LAST_NAME_1 ",
                    "$EMAIL_1 ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $FIRST_NAME_1 ",
                    " $LAST_NAME_1 ",
                    " $EMAIL_1 ",
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

        val result = userInputSanitizer.sanitize(input)

        assertEquals(FIRST_NAME_1, result.firstName)
        assertEquals(LAST_NAME_1, result.lastName)
        assertEquals(EMAIL_1, result.email)
    }
}

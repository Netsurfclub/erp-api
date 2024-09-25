package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UserInput
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
                    "username",
                    "password",
                    "confirmPassword",
                    "firstName",
                    "lastName",
                    "email",
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " username",
                    " password",
                    " confirmPassword",
                    " firstName",
                    " lastName",
                    " email",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "username ",
                    "password ",
                    "confirmPassword ",
                    "firstName ",
                    "lastName ",
                    "email ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " username ",
                    " password ",
                    " confirmPassword ",
                    " firstName ",
                    " lastName ",
                    " email ",
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
        firstName: String,
        lastName: String,
        email: String,
    ) {
        val userInput =
            UserInput(
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )

        val result = userInputSanitizer.sanitize(userInput)

        assertEquals("username", result.username)
        assertEquals("password", result.password)
        assertEquals("confirmPassword", result.confirmPassword)
        assertEquals("firstName", result.firstName)
        assertEquals("password", result.password)
        assertEquals("lastName", result.lastName)
        assertEquals("email", result.email)
    }
}

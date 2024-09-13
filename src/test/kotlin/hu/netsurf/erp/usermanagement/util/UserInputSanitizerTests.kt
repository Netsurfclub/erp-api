package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class UserInputSanitizerTests {
    private val userInputSanitizer: UserInputSanitizer = UserInputSanitizer(InputSanitizer())

    companion object {
        @JvmStatic
        fun userInputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username", "password", "confirmPassword", "firstName", "lastName", "email"),
                Arguments.of(" username", " password", " confirmPassword", " firstName", " lastName", " email"),
                Arguments.of("username ", "password ", "confirmPassword ", "firstName ", "lastName ", "email "),
                Arguments.of(" username ", " password ", " confirmPassword ", " firstName ", " lastName ", " email "),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputParams")
    fun `sanitize tests`(
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

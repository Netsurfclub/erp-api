package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.TestConstants.CONFIRM_PASSWORD_INPUT
import hu.netsurf.erp.TestConstants.EMAIL_INPUT
import hu.netsurf.erp.TestConstants.FIRST_NAME_INPUT
import hu.netsurf.erp.TestConstants.LAST_NAME_INPUT
import hu.netsurf.erp.TestConstants.PASSWORD_INPUT
import hu.netsurf.erp.TestConstants.USERNAME_INPUT
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
                    USERNAME_INPUT,
                    PASSWORD_INPUT,
                    CONFIRM_PASSWORD_INPUT,
                    FIRST_NAME_INPUT,
                    LAST_NAME_INPUT,
                    EMAIL_INPUT,
                ),
                Arguments.of(
                    "one whitespace on left side",
                    " $USERNAME_INPUT",
                    " $PASSWORD_INPUT",
                    " $CONFIRM_PASSWORD_INPUT",
                    " $FIRST_NAME_INPUT",
                    " $LAST_NAME_INPUT",
                    " $EMAIL_INPUT",
                ),
                Arguments.of(
                    "one whitespace on right side",
                    "$USERNAME_INPUT ",
                    "$PASSWORD_INPUT ",
                    "$CONFIRM_PASSWORD_INPUT ",
                    "$FIRST_NAME_INPUT ",
                    "$LAST_NAME_INPUT ",
                    "$EMAIL_INPUT ",
                ),
                Arguments.of(
                    "whitespaces on both right and left side",
                    " $USERNAME_INPUT ",
                    " $PASSWORD_INPUT ",
                    " $CONFIRM_PASSWORD_INPUT ",
                    " $FIRST_NAME_INPUT ",
                    " $LAST_NAME_INPUT ",
                    " $EMAIL_INPUT ",
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

        assertEquals(USERNAME_INPUT, result.username)
        assertEquals(PASSWORD_INPUT, result.password)
        assertEquals(CONFIRM_PASSWORD_INPUT, result.confirmPassword)
        assertEquals(FIRST_NAME_INPUT, result.firstName)
        assertEquals(LAST_NAME_INPUT, result.lastName)
        assertEquals(EMAIL_INPUT, result.email)
    }
}

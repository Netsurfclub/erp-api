package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.TestConstants.EMAIL_1
import hu.netsurf.erp.TestConstants.FIRST_NAME_1
import hu.netsurf.erp.TestConstants.INVALID_EMAIL_1
import hu.netsurf.erp.TestConstants.INVALID_FIRST_NAME_1
import hu.netsurf.erp.TestConstants.INVALID_FIRST_NAME_STARTS_WITH_NUMBER
import hu.netsurf.erp.TestConstants.INVALID_LAST_NAME_1
import hu.netsurf.erp.TestConstants.INVALID_LAST_NAME_STARTS_WITH_NUMBER
import hu.netsurf.erp.TestConstants.LAST_NAME_1
import hu.netsurf.erp.TestConstants.PASSWORD
import hu.netsurf.erp.TestConstants.USERNAME_1
import hu.netsurf.erp.testobject.UserInputTestObject
import hu.netsurf.erp.usermanagement.model.UserInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UserInputValidatorTests {
    private val userInputValidator: UserInputValidator = UserInputValidator()

    companion object {
        @JvmStatic
        fun userInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username is empty", UserInputTestObject.userInput1WithEmptyUsername()),
                Arguments.of("password is empty", UserInputTestObject.userInput1WithEmptyPassword()),
                Arguments.of("confirmPassword is empty", UserInputTestObject.userInput1WithEmptyConfirmPassword()),
                Arguments.of("firstName is empty", UserInputTestObject.userInput1WithEmptyFirstName()),
                Arguments.of("lastName is empty", UserInputTestObject.userInput1WithEmptyLastName()),
                Arguments.of("email is empty", UserInputTestObject.userInput1WithEmptyEmail()),
            )

        @JvmStatic
        fun userInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username is too short", UserInputTestObject.userInput1WithShortUsername()),
                Arguments.of("username is too long", UserInputTestObject.userInput1WithLongUsername()),
                Arguments.of("password is too short", UserInputTestObject.userInput1WithShortPassword()),
                Arguments.of("password is too long", UserInputTestObject.userInput1WithLongPassword()),
                Arguments.of("firstName is too short", UserInputTestObject.userInput1WithShortFirstName()),
                Arguments.of("firstName is too long", UserInputTestObject.userInput1WithLongFirstName()),
                Arguments.of("lastName is too short", UserInputTestObject.userInput1WithShortLastName()),
                Arguments.of("lastName is too long", UserInputTestObject.userInput1WithLongLastName()),
                Arguments.of("email is too short", UserInputTestObject.userInput1WithShortEmail()),
                Arguments.of("email is too long", UserInputTestObject.userInput1WithLongEmail()),
            )

        @JvmStatic
        fun firstNameParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("bence", INVALID_FIRST_NAME_1),
                Arguments.of("1firstName", INVALID_FIRST_NAME_STARTS_WITH_NUMBER),
            )

        @JvmStatic
        fun lastNameParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("juhász", INVALID_LAST_NAME_1),
                Arguments.of("1lastName", INVALID_LAST_NAME_STARTS_WITH_NUMBER),
            )
    }

    @Test
    fun `validate test happy path`() {
        val result = userInputValidator.validate(UserInputTestObject.userInput1())
        assertEquals("Validation success.", result.message)
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        userInput: UserInput,
    ) {
        val result = userInputValidator.validate(userInput)
        assertEquals("Üres mező.", result.message)
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        userInput: UserInput,
    ) {
        val result = userInputValidator.validate(userInput)
        assertEquals("A mező hossza nem megfelelő.", result.message)
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("firstNameParams")
    fun `validate tests unhappy path - invalid first name`(
        testCase: String,
        firstName: String,
    ) {
        val userInput =
            UserInput(
                username = USERNAME_1,
                password = PASSWORD,
                confirmPassword = PASSWORD,
                firstName = firstName,
                lastName = LAST_NAME_1,
                email = EMAIL_1,
            )

        val result = userInputValidator.validate(userInput)
        assertEquals("Nem megfelelő keresztnév formátum.", result.message)
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("lastNameParams")
    fun `validate tests unhappy path - invalid last name`(
        testCase: String,
        lastName: String,
    ) {
        val userInput =
            UserInput(
                username = USERNAME_1,
                password = PASSWORD,
                confirmPassword = PASSWORD,
                firstName = FIRST_NAME_1,
                lastName = lastName,
                email = INVALID_EMAIL_1,
            )

        val result = userInputValidator.validate(userInput)
        assertEquals("Nem megfelelő vezetéknév formátum.", result.message)
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        val result = userInputValidator.validate(UserInputTestObject.userInput1WithInvalidEmail())
        assertEquals("Nem megfelelő e-mail cím formátum.", result.message)
    }

    @Test
    fun `validate test unhappy path - password and confirm password not matches`() {
        val result = userInputValidator.validate(UserInputTestObject.userInput1WithInvalidConfirmPassword())
        assertEquals("A jelszavak nem egyeznek.", result.message)
    }
}

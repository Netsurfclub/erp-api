package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_FIRST_NAME_CONTAINS_DIGIT
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_FIRST_NAME_STARTS_WITH_LOWERCASE_CHARACTER
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_LAST_NAME_CONTAINS_DIGIT
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_LAST_NAME_STARTS_WITH_LOWERCASE_CHARACTER
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.PasswordAndConfirmPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UserInput
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyConfirmPassword
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyEmail
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyFirstName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyLastName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyPassword
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithEmptyUsername
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithInvalidConfirmPassword
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithInvalidEmail
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithLongEmail
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithLongFirstName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithLongLastName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithLongUsername
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithShortEmail
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithShortFirstName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithShortLastName
import hu.netsurf.erp.usermanagement.testobject.UserInputTestObject.Companion.userInput1WithShortUsername
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
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
                Arguments.of("username is empty", userInput1WithEmptyUsername()),
                Arguments.of("password is empty", userInput1WithEmptyPassword()),
                Arguments.of("confirmPassword is empty", userInput1WithEmptyConfirmPassword()),
                Arguments.of("firstName is empty", userInput1WithEmptyFirstName()),
                Arguments.of("lastName is empty", userInput1WithEmptyLastName()),
                Arguments.of("email is empty", userInput1WithEmptyEmail()),
            )

        @JvmStatic
        fun userInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username is too short", userInput1WithShortUsername()),
                Arguments.of("username is too long", userInput1WithLongUsername()),
                Arguments.of("firstName is too short", userInput1WithShortFirstName()),
                Arguments.of("firstName is too long", userInput1WithLongFirstName()),
                Arguments.of("lastName is too short", userInput1WithShortLastName()),
                Arguments.of("lastName is too long", userInput1WithLongLastName()),
                Arguments.of("email is too short", userInput1WithShortEmail()),
                Arguments.of("email is too long", userInput1WithLongEmail()),
            )

        @JvmStatic
        fun firstNameParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("bence", INVALID_FIRST_NAME_STARTS_WITH_LOWERCASE_CHARACTER),
                Arguments.of("F1rstName", INVALID_FIRST_NAME_CONTAINS_DIGIT),
            )

        @JvmStatic
        fun lastNameParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("juhász", INVALID_LAST_NAME_STARTS_WITH_LOWERCASE_CHARACTER),
                Arguments.of("LastNam3", INVALID_LAST_NAME_CONTAINS_DIGIT),
            )

        @JvmStatic
        fun invalidPasswordParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("no number", "p@sSwOrD"),
                Arguments.of("no lowercase character", "P@SSW0RD"),
                Arguments.of("no uppercase character", "p@ssword"),
                Arguments.of("no special character", "pAsSwOrD"),
                Arguments.of("too short", "p"),
                Arguments.of("too long", "p@sSw0rDp@sSw0rD"),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            userInputValidator.validate(userInput1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        userInput: UserInput,
    ) {
        assertThrows<EmptyFieldException> {
            userInputValidator.validate(userInput)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        userInput: UserInput,
    ) {
        assertThrows<InvalidLengthException> {
            userInputValidator.validate(userInput)
        }
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

        assertThrows<InvalidFirstNameFormatException> {
            userInputValidator.validate(userInput)
        }
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
                email = EMAIL_1,
            )

        assertThrows<InvalidLastNameFormatException> {
            userInputValidator.validate(userInput)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            userInputValidator.validate(userInput1WithInvalidEmail())
        }
    }

    @Test
    fun `validate test unhappy path - password and confirm password not matches`() {
        assertThrows<PasswordAndConfirmPasswordNotMatchesException> {
            userInputValidator.validate(userInput1WithInvalidConfirmPassword())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidPasswordParams")
    fun `validate test unhappy path - invalid password format`(
        testCase: String,
        password: String,
    ) {
        val userInput =
            UserInput(
                username = USERNAME_1,
                password = password,
                confirmPassword = password,
                firstName = FIRST_NAME_1,
                lastName = LAST_NAME_1,
                email = EMAIL_1,
            )

        assertThrows<InvalidPasswordFormatException> {
            userInputValidator.validate(userInput)
        }
    }
}

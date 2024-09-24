package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.testobject.UserInputTestObject
import hu.netsurf.erp.usermanagement.exception.ConfirmPasswordException
import hu.netsurf.erp.usermanagement.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.model.UserInput
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
                Arguments.of("bence", "bence"),
                Arguments.of("1firstName", "1firstName"),
            )

        @JvmStatic
        fun lastNameParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("juhász", "juhász"),
                Arguments.of("1lastName", "1lastName"),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            userInputValidator.validate(UserInputTestObject.userInput1())
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
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = firstName,
                lastName = "Juhász",
                email = "bjuhasznetsurfclub.hu",
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
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = "Bence",
                lastName = lastName,
                email = "bjuhasznetsurfclub.hu",
            )

        assertThrows<InvalidLastNameFormatException> {
            userInputValidator.validate(userInput)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            userInputValidator.validate(UserInputTestObject.userInput1WithInvalidEmail())
        }
    }

    @Test
    fun `validate test unhappy path - password and confirm password are not equals`() {
        assertThrows<ConfirmPasswordException> {
            userInputValidator.validate(UserInputTestObject.userInput1WithInvalidConfirmPassword())
        }
    }
}

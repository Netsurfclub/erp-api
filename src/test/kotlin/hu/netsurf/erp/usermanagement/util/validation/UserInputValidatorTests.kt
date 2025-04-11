package hu.netsurf.erp.usermanagement.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_FIRST_NAME_CONTAINS_DIGIT
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_FIRST_NAME_STARTS_WITH_LOWERCASE_CHARACTER
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_LAST_NAME_CONTAINS_DIGIT
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_LAST_NAME_STARTS_WITH_LOWERCASE_CHARACTER
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithEmptyEmail
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithEmptyFirstName
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithEmptyLastName
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithInvalidEmail
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithLongEmail
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithLongFirstName
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithLongLastName
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithShortEmail
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithShortFirstName
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1WithShortLastName
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
                Arguments.of("firstName is empty", input1WithEmptyFirstName()),
                Arguments.of("lastName is empty", input1WithEmptyLastName()),
                Arguments.of("email is empty", input1WithEmptyEmail()),
            )

        @JvmStatic
        fun userInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("firstName is too short", input1WithShortFirstName()),
                Arguments.of("firstName is too long", input1WithLongFirstName()),
                Arguments.of("lastName is too short", input1WithShortLastName()),
                Arguments.of("lastName is too long", input1WithLongLastName()),
                Arguments.of("email is too short", input1WithShortEmail()),
                Arguments.of("email is too long", input1WithLongEmail()),
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
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            userInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        input: CreateUserInput,
    ) {
        assertThrows<EmptyFieldException> {
            userInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        input: CreateUserInput,
    ) {
        assertThrows<InvalidLengthException> {
            userInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("firstNameParams")
    fun `validate tests unhappy path - invalid first name`(
        testCase: String,
        firstName: String,
    ) {
        val input =
            CreateUserInput(
                firstName = firstName,
                lastName = LAST_NAME_1,
                email = EMAIL_1,
            )

        assertThrows<InvalidFirstNameFormatException> {
            userInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("lastNameParams")
    fun `validate tests unhappy path - invalid last name`(
        testCase: String,
        lastName: String,
    ) {
        val input =
            CreateUserInput(
                firstName = FIRST_NAME_1,
                lastName = lastName,
                email = EMAIL_1,
            )

        assertThrows<InvalidLastNameFormatException> {
            userInputValidator.validate(input)
        }
    }

    @Test
    fun `validate test unhappy path - invalid email address`() {
        assertThrows<InvalidEmailAddressFormatException> {
            userInputValidator.validate(input1WithInvalidEmail())
        }
    }
}

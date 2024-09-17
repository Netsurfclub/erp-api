package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.exception.ConfirmPasswordException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
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
        val userInput =
            UserInput(
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "bjuhasz@netsurfclub.hu",
            )

        assertDoesNotThrow {
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
        val userInput =
            UserInput(
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "bjuhasznetsurfclub.hu",
            )

        assertThrows<InvalidEmailAddressFormatException> {
            userInputValidator.validate(userInput)
        }
    }

    @Test
    fun `validate test unhappy path - password and confirm password not equals`() {
        val userInput =
            UserInput(
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "CoNfIrMpAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "bjuhasz@netsurfclub.hu",
            )

        assertThrows<ConfirmPasswordException> {
            userInputValidator.validate(userInput)
        }
    }
}

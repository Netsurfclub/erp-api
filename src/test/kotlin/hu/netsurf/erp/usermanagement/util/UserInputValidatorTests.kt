package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.exception.ConfirmPasswordException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.model.UserInput
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class UserInputValidatorTests {
    private val emailAddressValidator: EmailAddressValidator = EmailAddressValidator()
    private val userInputValidator: UserInputValidator = UserInputValidator(emailAddressValidator)

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

    @Test
    fun `validate test unhappy path - invalid email address`() {
        val userInput =
            UserInput(
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "email",
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

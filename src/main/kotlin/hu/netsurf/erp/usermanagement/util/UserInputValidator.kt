package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator(
    private val emailAddressValidator: EmailAddressValidator,
) {
    fun validate(userInput: UserInput) {
        // 1. Empty check input fields.
        // 2. Checks length for input fields.
        // 3. 'firstName' field starts with uppercase character.
        // 4. 'lastName' field starts with uppercase character.

        if (!emailAddressValidator.isValid(userInput.email)) {
            throw InvalidEmailAddressFormatException()
        }

        // 6. 'password' and 'confirmPassword' fields equality check.
    }
}

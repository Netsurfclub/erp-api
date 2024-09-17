package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.constant.ValidationConstants.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.usermanagement.exception.ConfirmPasswordException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator {
    fun validate(userInput: UserInput) {
        // 1. Empty check input fields.

        // 2. Checks length for input fields.

        // 3. 'firstName' field starts with uppercase character.

        // 4. 'lastName' field starts with uppercase character.

        if (!userInput.emailAddressIsValid(EMAIL_ADDRESS_REGEX)) {
            throw InvalidEmailAddressFormatException()
        }

        if (!userInput.passwordAndConfirmPasswordAreMatching()) {
            throw ConfirmPasswordException()
        }
    }
}

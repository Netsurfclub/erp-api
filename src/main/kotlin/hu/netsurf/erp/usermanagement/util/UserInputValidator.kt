package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.exception.ConfirmPasswordException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator {
    fun validate(userInput: UserInput) {
        // 1. Empty check input fields.

        // 2. Checks length for input fields.

        if (!userInput.firstNameStartsWithUpperCaseCharacter()) {
            throw InvalidFirstNameFormatException()
        }

        if (!userInput.lastNameStartsWithUpperCaseCharacter()) {
            throw InvalidLastNameFormatException()
        }

        if (!userInput.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }

        if (!userInput.passwordAndConfirmPasswordAreMatching()) {
            throw ConfirmPasswordException()
        }
    }
}

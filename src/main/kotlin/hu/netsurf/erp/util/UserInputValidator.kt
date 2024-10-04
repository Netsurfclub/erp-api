package hu.netsurf.erp.util

import hu.netsurf.erp.exception.EmptyFieldException
import hu.netsurf.erp.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.exception.InvalidLastNameFormatException
import hu.netsurf.erp.exception.InvalidLengthException
import hu.netsurf.erp.exception.PasswordAndConfirmPasswordNotMatchesException
import hu.netsurf.erp.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator {
    fun validate(userInput: UserInput) {
        if (
            userInput.usernameIsEmpty() ||
            userInput.passwordIsEmpty() ||
            userInput.confirmPasswordIsEmpty() ||
            userInput.firstNameIsEmpty() ||
            userInput.lastNameIsEmpty() ||
            userInput.emailIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (
            userInput.usernameIsShort() ||
            userInput.usernameIsLong() ||
            userInput.passwordIsShort() ||
            userInput.passwordIsLong() ||
            userInput.firstNameIsShort() ||
            userInput.firstNameIsLong() ||
            userInput.lastNameIsShort() ||
            userInput.lastNameIsLong() ||
            userInput.emailIsShort() ||
            userInput.emailIsLong()
        ) {
            throw InvalidLengthException()
        }

        if (!userInput.firstNameStartsWithUpperCaseCharacter()) {
            throw InvalidFirstNameFormatException()
        }

        if (!userInput.lastNameStartsWithUpperCaseCharacter()) {
            throw InvalidLastNameFormatException()
        }

        if (!userInput.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }

        if (!userInput.passwordAndConfirmPasswordMatches()) {
            throw PasswordAndConfirmPasswordNotMatchesException()
        }
    }
}

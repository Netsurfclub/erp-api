package hu.netsurf.erp.util

import hu.netsurf.erp.model.UserInput
import hu.netsurf.erp.model.UserInputValidationResult
import org.springframework.stereotype.Component

@Component
class UserInputValidator {
    fun validate(userInput: UserInput): UserInputValidationResult {
        if (
            userInput.usernameIsEmpty() ||
            userInput.passwordIsEmpty() ||
            userInput.confirmPasswordIsEmpty() ||
            userInput.firstNameIsEmpty() ||
            userInput.lastNameIsEmpty() ||
            userInput.emailIsEmpty()
        ) {
            return UserInputValidationResult.emptyField()
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
            return UserInputValidationResult.invalidLength()
        }

        if (!userInput.firstNameStartsWithUpperCaseCharacter()) {
            return UserInputValidationResult.invalidFirstNameFormat()
        }

        if (!userInput.lastNameStartsWithUpperCaseCharacter()) {
            return UserInputValidationResult.invalidLastNameFormat()
        }

        if (!userInput.emailAddressIsValid()) {
            return UserInputValidationResult.invalidEmailAddressFormat()
        }

        if (!userInput.passwordAndConfirmPasswordMatches()) {
            return UserInputValidationResult.passwordAndConfirmPasswordNotMatches()
        }

        return UserInputValidationResult.success()
    }
}

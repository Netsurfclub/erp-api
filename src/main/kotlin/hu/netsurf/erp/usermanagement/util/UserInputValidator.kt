﻿package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.PasswordAndConfirmPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UserInput
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

        if (userInput.firstNameContainsDigit()) {
            throw InvalidFirstNameFormatException()
        }

        if (userInput.lastNameContainsDigit()) {
            throw InvalidLastNameFormatException()
        }

        if (!userInput.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }

        if (!userInput.passwordAndConfirmPasswordMatches()) {
            throw PasswordAndConfirmPasswordNotMatchesException()
        }

        if (!userInput.passwordIsValid()) {
            throw InvalidPasswordFormatException()
        }
    }
}

package hu.netsurf.erp.usermanagement.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidFirstNameFormatException
import hu.netsurf.erp.usermanagement.exception.InvalidLastNameFormatException
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator {
    fun validate(input: CreateUserInput) {
        if (
            input.firstNameIsEmpty() ||
            input.lastNameIsEmpty() ||
            input.emailIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (
            input.firstNameIsShort() ||
            input.firstNameIsLong() ||
            input.lastNameIsShort() ||
            input.lastNameIsLong() ||
            input.emailIsShort() ||
            input.emailIsLong()
        ) {
            throw InvalidLengthException()
        }

        if (!input.firstNameStartsWithUpperCaseCharacter()) {
            throw InvalidFirstNameFormatException()
        }

        if (!input.lastNameStartsWithUpperCaseCharacter()) {
            throw InvalidLastNameFormatException()
        }

        if (input.firstNameContainsDigit()) {
            throw InvalidFirstNameFormatException()
        }

        if (input.lastNameContainsDigit()) {
            throw InvalidLastNameFormatException()
        }

        if (!input.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }
    }
}

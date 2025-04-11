package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.PasswordAndConfirmPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import org.springframework.stereotype.Component

@Component
class ProfileInputValidator {
    fun validate(input: CreateProfileInput) {
        if (
            input.usernameIsEmpty() ||
            input.passwordIsEmpty() ||
            input.confirmPasswordIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (
            input.usernameIsShort() ||
            input.usernameIsLong()
        ) {
            throw InvalidLengthException()
        }

        if (!input.passwordAndConfirmPasswordMatches()) {
            throw PasswordAndConfirmPasswordNotMatchesException()
        }

        if (!input.passwordIsValid()) {
            throw InvalidPasswordFormatException()
        }
    }
}

package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputValidator {
    fun validate(input: UpdateUserPasswordInput) {
        if (
            input.currentPasswordIsEmpty() ||
            input.newPasswordIsEmpty() ||
            input.confirmNewPasswordIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (!input.newPasswordIsValid()) {
            throw InvalidPasswordFormatException()
        }

        if (!input.newPasswordAndConfirmNewPasswordMatches()) {
            throw NewPasswordAndConfirmNewPasswordNotMatchesException()
        }
    }
}

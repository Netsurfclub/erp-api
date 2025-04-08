package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputValidator {
    fun validate(
        input: UpdateUserPasswordInput,
        passwordInDatabase: String,
    ) {
        if (
            input.currentPasswordIsEmpty() ||
            input.newPasswordIsEmpty() ||
            input.confirmNewPasswordIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (!input.currentPasswordAndPasswordInDatabaseMatches(passwordInDatabase)) {
            throw CurrentPasswordAndPasswordInDatabaseNotMatchesException()
        }

        if (!input.newPasswordAndConfirmNewPasswordMatches()) {
            throw NewPasswordAndConfirmNewPasswordNotMatchesException()
        }
    }
}

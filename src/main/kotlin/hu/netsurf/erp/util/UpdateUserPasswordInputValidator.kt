package hu.netsurf.erp.util

import hu.netsurf.erp.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.exception.EmptyFieldException
import hu.netsurf.erp.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.exception.NewPasswordAndPasswordInDatabaseMatchesException
import hu.netsurf.erp.input.UpdateUserPasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputValidator {
    fun validate(
        updateUserPasswordInput: UpdateUserPasswordInput,
        passwordInDatabase: String,
    ) {
        if (
            updateUserPasswordInput.currentPasswordIsEmpty() ||
            updateUserPasswordInput.newPasswordIsEmpty() ||
            updateUserPasswordInput.confirmNewPasswordIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (!updateUserPasswordInput.currentPasswordAndPasswordInDatabaseMatches(passwordInDatabase)) {
            throw CurrentPasswordAndPasswordInDatabaseNotMatchesException()
        }

        if (updateUserPasswordInput.newPasswordAndCurrentPasswordInDatabaseMatches(passwordInDatabase)) {
            throw NewPasswordAndPasswordInDatabaseMatchesException()
        }

        if (!updateUserPasswordInput.newPasswordAndConfirmNewPasswordMatches()) {
            throw NewPasswordAndConfirmNewPasswordNotMatchesException()
        }
    }
}

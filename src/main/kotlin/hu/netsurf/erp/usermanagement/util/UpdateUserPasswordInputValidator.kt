package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.exception.ConfirmCurrentPasswordException
import hu.netsurf.erp.usermanagement.exception.ConfirmNewPasswordException
import hu.netsurf.erp.usermanagement.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
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
            throw ConfirmCurrentPasswordException()
        }

        if (!updateUserPasswordInput.newPasswordAndConfirmNewPasswordMatches()) {
            throw ConfirmNewPasswordException()
        }
    }
}

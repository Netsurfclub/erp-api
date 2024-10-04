package hu.netsurf.erp.util

import hu.netsurf.erp.model.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInputValidationResult
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputValidator {
    fun validate(
        updateUserPasswordInput: UpdateUserPasswordInput,
        passwordInDatabase: String,
    ): UpdateUserPasswordInputValidationResult {
        if (
            updateUserPasswordInput.currentPasswordIsEmpty() ||
            updateUserPasswordInput.newPasswordIsEmpty() ||
            updateUserPasswordInput.confirmNewPasswordIsEmpty()
        ) {
            return UpdateUserPasswordInputValidationResult.emptyField()
        }

        if (!updateUserPasswordInput.currentPasswordAndPasswordInDatabaseMatches(passwordInDatabase)) {
            return UpdateUserPasswordInputValidationResult.currentPasswordAndPasswordInDatabaseNotMatches()
        }

        if (updateUserPasswordInput.newPasswordAndCurrentPasswordInDatabaseMatches(passwordInDatabase)) {
            return UpdateUserPasswordInputValidationResult.newPasswordAndPasswordInDatabaseMatches()
        }

        if (!updateUserPasswordInput.newPasswordAndConfirmNewPasswordMatches()) {
            return UpdateUserPasswordInputValidationResult.newPasswordAndConfirmNewPasswordMatches()
        }

        return UpdateUserPasswordInputValidationResult.success()
    }
}

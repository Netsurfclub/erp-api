package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(updateUserPasswordInput: UpdateUserPasswordInput): UpdateUserPasswordInput =
        UpdateUserPasswordInput(
            userId = updateUserPasswordInput.userId,
            currentPassword = inputSanitizer.sanitize(updateUserPasswordInput.currentPassword),
            newPassword = inputSanitizer.sanitize(updateUserPasswordInput.newPassword),
            confirmNewPassword = inputSanitizer.sanitize(updateUserPasswordInput.confirmNewPassword),
        )
}

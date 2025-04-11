package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateProfilePasswordInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: UpdateProfilePasswordInput): UpdateProfilePasswordInput =
        UpdateProfilePasswordInput(
            id = input.id,
            currentPassword = inputSanitizer.sanitize(input.currentPassword),
            newPassword = inputSanitizer.sanitize(input.newPassword),
            confirmNewPassword = inputSanitizer.sanitize(input.confirmNewPassword),
        )
}

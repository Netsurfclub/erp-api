package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import org.springframework.stereotype.Component

@Component
class ProfileInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateProfileInput): CreateProfileInput =
        CreateProfileInput(
            userId = input.userId,
            username = inputSanitizer.sanitize(input.username),
            password = inputSanitizer.sanitize(input.password),
            confirmPassword = inputSanitizer.sanitize(input.confirmPassword),
        )
}

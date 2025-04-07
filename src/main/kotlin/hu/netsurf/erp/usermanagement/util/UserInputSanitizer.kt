package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import org.springframework.stereotype.Component

@Component
class UserInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateUserInput): CreateUserInput =
        CreateUserInput(
            username = inputSanitizer.sanitize(input.username),
            password = inputSanitizer.sanitize(input.password),
            confirmPassword = inputSanitizer.sanitize(input.confirmPassword),
            firstName = inputSanitizer.sanitize(input.firstName),
            lastName = inputSanitizer.sanitize(input.lastName),
            email = inputSanitizer.sanitize(input.email),
        )
}

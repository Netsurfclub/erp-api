package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputValidator(
    private val inputSanitizer: InputSanitizer,
) {
    fun validate(userInput: UserInput) {
        val sanitizedUserInput = sanitize(userInput)

        // 1. Empty check input fields.
        // 2. Checks length for input fields.
        // 3. 'firstName' field starts with uppercase character.
        // 4. 'lastName' field starts with uppercase character.
        // 5. 'email' field should contain a '@' character (email regex).
        // 6. 'password' and 'confirmPassword' fields equality check.
    }

    private fun sanitize(userInput: UserInput): UserInput =
        UserInput(
            username = inputSanitizer.sanitize(userInput.username),
            password = inputSanitizer.sanitize(userInput.password),
            confirmPassword = inputSanitizer.sanitize(userInput.confirmPassword),
            firstName = inputSanitizer.sanitize(userInput.firstName),
            lastName = inputSanitizer.sanitize(userInput.lastName),
            email = inputSanitizer.sanitize(userInput.email),
        )
}

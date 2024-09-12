package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserValidator {
    fun validate(userInput: UserInput) {
        // 0. Sanitize all input fields.
        // 1. Empty check input fields.
        // 2. Checks length for input fields.
        // 3. 'firstName' field starts with uppercase character.
        // 4. 'lastName' field starts with uppercase character.
        // 5. 'email' field should contain a '@' character (email regex).
        // 6. 'password' and 'confirmPassword' fields equality check.
    }
}

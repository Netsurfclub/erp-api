package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.model.UserInput
import org.springframework.stereotype.Component

@Component
class UserValidator {
    fun validate(userInput: UserInput) {
        // 1. Empty checks.
        // 2. Length checks.
        // 3. 'firstName' starts with uppercase character.
        // 4. 'lastName' starts with uppercase character.
        // 5. 'email' field should contain a '@' character => Might need an email regex.
        // 6. 'password' and 'confirmPassword' fields equality check.
    }
}

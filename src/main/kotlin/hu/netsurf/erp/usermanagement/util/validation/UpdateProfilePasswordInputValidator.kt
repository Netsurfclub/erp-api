package hu.netsurf.erp.usermanagement.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateProfilePasswordInputValidator {
    fun validate(input: UpdateProfilePasswordInput) {
        if (
            input.currentPasswordIsEmpty() ||
            input.newPasswordIsEmpty() ||
            input.confirmNewPasswordIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (!input.newPasswordIsValid()) {
            throw InvalidPasswordFormatException()
        }

        if (!input.newPasswordAndConfirmNewPasswordMatches()) {
            throw NewPasswordAndConfirmNewPasswordNotMatchesException()
        }
    }
}

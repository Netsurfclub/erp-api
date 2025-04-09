package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import org.springframework.stereotype.Component

@Component
class UpdateUserPasswordInputValidator(
    private val passwordUtil: PasswordUtil,
) {
    fun validate(
        input: UpdateUserPasswordInput,
        passwordInDatabase: String,
    ) {
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

        if (!passwordUtil.verify(input.currentPassword, passwordInDatabase)) {
            throw CurrentPasswordAndPasswordInDatabaseNotMatchesException()
        }

        if (!input.newPasswordAndConfirmNewPasswordMatches()) {
            throw NewPasswordAndConfirmNewPasswordNotMatchesException()
        }
    }
}

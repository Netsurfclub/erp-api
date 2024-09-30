package hu.netsurf.erp.usermanagement.model

class UpdateUserPasswordInputValidationResult private constructor(
    val message: String,
) {
    companion object {
        fun emptyField(): UpdateUserPasswordInputValidationResult = UpdateUserPasswordInputValidationResult("Üres mező.")

        fun currentPasswordAndPasswordInDatabaseNotMatches(): UpdateUserPasswordInputValidationResult =
            UpdateUserPasswordInputValidationResult("A jelenlegi jelszó hibás.")

        fun newPasswordAndPasswordInDatabaseMatches(): UpdateUserPasswordInputValidationResult =
            UpdateUserPasswordInputValidationResult("Nem adható meg új jelszónak a jelenlegi jelszó.")

        fun newPasswordAndConfirmNewPasswordMatches(): UpdateUserPasswordInputValidationResult =
            UpdateUserPasswordInputValidationResult("A jelszó megerősítése sikertelen.")

        fun success(): UpdateUserPasswordInputValidationResult = UpdateUserPasswordInputValidationResult("Validation success.")
    }
}

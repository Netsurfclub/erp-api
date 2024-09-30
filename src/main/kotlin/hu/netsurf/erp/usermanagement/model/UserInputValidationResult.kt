package hu.netsurf.erp.usermanagement.model

class UserInputValidationResult private constructor(
    val message: String,
) {
    companion object {
        fun emptyField(): UserInputValidationResult = UserInputValidationResult("Üres mező.")

        fun invalidLength(): UserInputValidationResult = UserInputValidationResult("A mező hossza nem megfelelő.")

        fun invalidFirstNameFormat(): UserInputValidationResult = UserInputValidationResult("Nem megfelelő keresztnév formátum.")

        fun invalidLastNameFormat(): UserInputValidationResult = UserInputValidationResult("Nem megfelelő vezetéknév formátum.")

        fun invalidEmailAddressFormat(): UserInputValidationResult = UserInputValidationResult("Nem megfelelő e-mail cím formátum.")

        fun passwordAndConfirmPasswordNotMatches(): UserInputValidationResult = UserInputValidationResult("A jelszavak nem egyeznek.")

        fun success(): UserInputValidationResult = UserInputValidationResult("Validation success.")
    }
}

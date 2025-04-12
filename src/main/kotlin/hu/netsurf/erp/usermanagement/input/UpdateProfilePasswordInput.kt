package hu.netsurf.erp.usermanagement.input

import hu.netsurf.erp.usermanagement.constant.RegexPatterns.PASSWORD_REGEX

data class UpdateProfilePasswordInput(
    val id: Int,
    val currentPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
) {
    fun currentPasswordIsEmpty(): Boolean = currentPassword.isEmpty()

    fun newPasswordIsEmpty(): Boolean = newPassword.isEmpty()

    fun confirmNewPasswordIsEmpty(): Boolean = confirmNewPassword.isEmpty()

    fun newPasswordIsValid(): Boolean = newPassword.matches(Regex(PASSWORD_REGEX))

    fun newPasswordAndConfirmNewPasswordMatches(): Boolean = newPassword == confirmNewPassword
}

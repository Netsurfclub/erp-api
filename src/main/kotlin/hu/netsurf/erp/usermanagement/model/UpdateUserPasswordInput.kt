package hu.netsurf.erp.usermanagement.model

data class UpdateUserPasswordInput(
    val userId: Int,
    val currentPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
) {
    fun currentPasswordIsEmpty(): Boolean = currentPassword.isEmpty()

    fun newPasswordIsEmpty(): Boolean = newPassword.isEmpty()

    fun confirmNewPasswordIsEmpty(): Boolean = confirmNewPassword.isEmpty()

    fun currentPasswordAndPasswordInDatabaseMatches(password: String): Boolean = currentPassword == password

    fun newPasswordAndConfirmNewPasswordMatches(): Boolean = newPassword == confirmNewPassword
}

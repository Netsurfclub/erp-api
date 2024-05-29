package hu.netsurf.erp.usermanagement.model

data class UpdateUserPasswordInput(
    val userId: Int,
    val currentPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
)

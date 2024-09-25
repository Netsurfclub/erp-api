package hu.netsurf.erp.usermanagement.model

data class UpdateUserPasswordInputResponse(
    val user: User,
    val errorMessage: String = "",
)

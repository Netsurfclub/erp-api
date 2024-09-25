package hu.netsurf.erp.usermanagement.model

data class UpdateUserPasswordInputResponse(
    val user: User? = null,
    val errorMessage: String = "",
)

package hu.netsurf.erp.usermanagement.model

data class CreateUserResponse(
    val user: User? = null,
    val errorMessage: String = "",
)

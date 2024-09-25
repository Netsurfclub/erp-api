package hu.netsurf.erp.usermanagement.model

data class CreateUserResponse(
    val user: User,
    val errorMessage: String = "",
)

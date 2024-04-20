package hu.netsurf.erp.usermanagement.model

data class UserInput(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val email: String,
)

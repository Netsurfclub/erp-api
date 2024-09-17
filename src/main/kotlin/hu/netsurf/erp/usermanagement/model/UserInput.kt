package hu.netsurf.erp.usermanagement.model

data class UserInput(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    fun firstNameStartsWithUpperCaseCharacter(): Boolean = firstName.first().isUpperCase()

    fun lastNameStartsWithUpperCaseCharacter(): Boolean = lastName.first().isUpperCase()

    fun emailAddressIsValid(pattern: String): Boolean = email.matches(Regex(pattern))

    fun passwordAndConfirmPasswordAreMatching(): Boolean = password == confirmPassword
}

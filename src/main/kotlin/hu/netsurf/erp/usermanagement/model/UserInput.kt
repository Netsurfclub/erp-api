package hu.netsurf.erp.usermanagement.model

import hu.netsurf.erp.common.regex.RegexPatterns.EMAIL_ADDRESS_REGEX

data class UserInput(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    fun usernameIsEmpty(): Boolean = username.isEmpty()

    fun passwordIsEmpty(): Boolean = password.isEmpty()

    fun confirmPasswordIsEmpty(): Boolean = confirmPassword.isEmpty()

    fun firstNameIsEmpty(): Boolean = firstName.isEmpty()

    fun lastNameIsEmpty(): Boolean = lastName.isEmpty()

    fun emailIsEmpty(): Boolean = email.isEmpty()

    fun firstNameStartsWithUpperCaseCharacter(): Boolean = firstName.first().isUpperCase()

    fun lastNameStartsWithUpperCaseCharacter(): Boolean = lastName.first().isUpperCase()

    fun emailAddressIsValid(): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun passwordAndConfirmPasswordAreMatching(): Boolean = password == confirmPassword
}

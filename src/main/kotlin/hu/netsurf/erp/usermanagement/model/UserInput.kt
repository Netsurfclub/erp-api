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
    fun firstNameStartsWithUpperCaseCharacter(): Boolean = firstName.first().isUpperCase()

    fun lastNameStartsWithUpperCaseCharacter(): Boolean = lastName.first().isUpperCase()

    fun emailAddressIsValid(): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun passwordAndConfirmPasswordAreMatching(): Boolean = password == confirmPassword
}

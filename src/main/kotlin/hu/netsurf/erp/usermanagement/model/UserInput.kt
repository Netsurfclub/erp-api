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

    fun usernameIsShort(): Boolean = username.length < 5

    fun usernameIsLong(): Boolean = username.length > 15

    fun passwordIsEmpty(): Boolean = password.isEmpty()

    fun passwordIsShort(): Boolean = password.length < 5

    fun passwordIsLong(): Boolean = password.length > 15

    fun confirmPasswordIsEmpty(): Boolean = confirmPassword.isEmpty()

    fun firstNameIsEmpty(): Boolean = firstName.isEmpty()

    fun firstNameIsShort(): Boolean = firstName.length <= 1

    fun firstNameIsLong(): Boolean = firstName.length > 255

    fun lastNameIsEmpty(): Boolean = lastName.isEmpty()

    fun lastNameIsShort(): Boolean = lastName.length <= 1

    fun lastNameIsLong(): Boolean = lastName.length > 255

    fun emailIsEmpty(): Boolean = email.isEmpty()

    fun emailIsShort(): Boolean = email.length <= 1

    fun emailIsLong(): Boolean = email.length > 75

    fun firstNameStartsWithUpperCaseCharacter(): Boolean = firstName.first().isUpperCase()

    fun lastNameStartsWithUpperCaseCharacter(): Boolean = lastName.first().isUpperCase()

    fun emailAddressIsValid(): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun passwordAndConfirmPasswordMatches(): Boolean = password == confirmPassword
}

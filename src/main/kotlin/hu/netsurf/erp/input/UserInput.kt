package hu.netsurf.erp.input

import hu.netsurf.erp.constant.RegexPatterns.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.constant.UserValidationConstants.EMAIL_MAX_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.EMAIL_MIN_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.FIRST_NAME_MAX_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.FIRST_NAME_MIN_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.LAST_NAME_MAX_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.LAST_NAME_MIN_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.PASSWORD_MAX_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.PASSWORD_MIN_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.USERNAME_MAX_LENGTH
import hu.netsurf.erp.constant.UserValidationConstants.USERNAME_MIN_LENGTH
import hu.netsurf.erp.model.User

data class UserInput(
    val username: String,
    val password: String,
    val confirmPassword: String,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
    fun usernameIsEmpty(): Boolean = username.isEmpty()

    fun usernameIsShort(): Boolean = username.length < USERNAME_MIN_LENGTH

    fun usernameIsLong(): Boolean = username.length > USERNAME_MAX_LENGTH

    fun passwordIsEmpty(): Boolean = password.isEmpty()

    fun passwordIsShort(): Boolean = password.length < PASSWORD_MIN_LENGTH

    fun passwordIsLong(): Boolean = password.length > PASSWORD_MAX_LENGTH

    fun confirmPasswordIsEmpty(): Boolean = confirmPassword.isEmpty()

    fun firstNameIsEmpty(): Boolean = firstName.isEmpty()

    fun firstNameIsShort(): Boolean = firstName.length <= FIRST_NAME_MIN_LENGTH

    fun firstNameIsLong(): Boolean = firstName.length > FIRST_NAME_MAX_LENGTH

    fun lastNameIsEmpty(): Boolean = lastName.isEmpty()

    fun lastNameIsShort(): Boolean = lastName.length <= LAST_NAME_MIN_LENGTH

    fun lastNameIsLong(): Boolean = lastName.length > LAST_NAME_MAX_LENGTH

    fun emailIsEmpty(): Boolean = email.isEmpty()

    fun emailIsShort(): Boolean = email.length <= EMAIL_MIN_LENGTH

    fun emailIsLong(): Boolean = email.length > EMAIL_MAX_LENGTH

    fun firstNameStartsWithUpperCaseCharacter(): Boolean = firstName.first().isUpperCase()

    fun lastNameStartsWithUpperCaseCharacter(): Boolean = lastName.first().isUpperCase()

    fun firstNameContainsDigit(): Boolean = firstName.any { it.isDigit() }

    fun lastNameContainsDigit(): Boolean = lastName.any { it.isDigit() }

    fun emailAddressIsValid(): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun passwordAndConfirmPasswordMatches(): Boolean = password == confirmPassword

    fun toUser(): User =
        User(
            username = this.username,
            password = this.password,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
        )
}

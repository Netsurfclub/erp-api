package hu.netsurf.erp.usermanagement.input

import hu.netsurf.erp.common.constant.RegexPatterns.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.usermanagement.constant.RegexPatterns.PASSWORD_REGEX
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.EMAIL_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.EMAIL_MIN_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.FIRST_NAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.FIRST_NAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.LAST_NAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.LAST_NAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.USERNAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.USERNAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.model.User

data class CreateUserInput(
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

    fun passwordIsValid(): Boolean = password.matches(Regex(PASSWORD_REGEX))

    fun toUser(): User =
        User(
            username = this.username,
            password = this.password,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            isDeleted = false,
        )
}

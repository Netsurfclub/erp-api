package hu.netsurf.erp.model

import hu.netsurf.erp.constant.RegexPatterns.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.constant.ValidationConstants.EMAIL_MAX_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.EMAIL_MIN_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.FIRST_NAME_MAX_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.FIRST_NAME_MIN_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.LAST_NAME_MAX_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.LAST_NAME_MIN_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.PASSWORD_MAX_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.PASSWORD_MIN_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.USERNAME_MAX_LENGTH
import hu.netsurf.erp.constant.ValidationConstants.USERNAME_MIN_LENGTH

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

    fun emailAddressIsValid(): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun passwordAndConfirmPasswordMatches(): Boolean = password == confirmPassword
}

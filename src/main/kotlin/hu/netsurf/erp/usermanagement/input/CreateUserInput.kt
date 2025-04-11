package hu.netsurf.erp.usermanagement.input

import hu.netsurf.erp.common.constant.CommonConstants.EMAIL_MAX_LENGTH
import hu.netsurf.erp.common.constant.CommonConstants.EMAIL_MIN_LENGTH
import hu.netsurf.erp.common.constant.RegexPatterns.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.FIRST_NAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.FIRST_NAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.LAST_NAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.LAST_NAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.model.User

data class CreateUserInput(
    val firstName: String,
    val lastName: String,
    val email: String,
) {
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

    fun toUser(): User =
        User(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
        )
}

package hu.netsurf.erp.usermanagement.input

import hu.netsurf.erp.usermanagement.constant.RegexPatterns.PASSWORD_REGEX
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.USERNAME_MAX_LENGTH
import hu.netsurf.erp.usermanagement.constant.UserValidationConstants.USERNAME_MIN_LENGTH
import hu.netsurf.erp.usermanagement.model.Profile
import hu.netsurf.erp.usermanagement.model.User

data class CreateProfileInput(
    val userId: Int,
    val username: String,
    val password: String,
    val confirmPassword: String,
) {
    fun usernameIsEmpty(): Boolean = username.isEmpty()

    fun usernameIsShort(): Boolean = username.length < USERNAME_MIN_LENGTH

    fun usernameIsLong(): Boolean = username.length > USERNAME_MAX_LENGTH

    fun passwordIsEmpty(): Boolean = password.isEmpty()

    fun confirmPasswordIsEmpty(): Boolean = confirmPassword.isEmpty()

    fun passwordAndConfirmPasswordMatches(): Boolean = password == confirmPassword

    fun passwordIsValid(): Boolean = password.matches(Regex(PASSWORD_REGEX))

    fun toProfile(): Profile =
        Profile(
            username = this.username,
            password = this.password,
            user = User(id = this.userId),
            isDeleted = false,
        )
}

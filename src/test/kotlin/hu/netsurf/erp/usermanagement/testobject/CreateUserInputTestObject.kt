package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_CONFIRM_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_LAST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_USERNAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.input.CreateUserInput

class CreateUserInputTestObject {
    companion object {
        fun input1(): CreateUserInput = build()

        fun input1WithEmptyUsername(): CreateUserInput = build(username = EMPTY_STRING)

        fun input1WithShortUsername(): CreateUserInput = build(username = "j")

        fun input1WithLongUsername(): CreateUserInput = build(username = LONG_USERNAME_1)

        fun input1WithEmptyPassword(): CreateUserInput = build(password = EMPTY_STRING)

        fun input1WithEmptyConfirmPassword(): CreateUserInput = build(confirmPassword = EMPTY_STRING)

        fun input1WithEmptyFirstName(): CreateUserInput = build(firstName = EMPTY_STRING)

        fun input1WithShortFirstName(): CreateUserInput = build(firstName = "B")

        fun input1WithLongFirstName(): CreateUserInput = build(firstName = LONG_FIRST_NAME_1)

        fun input1WithEmptyLastName(): CreateUserInput = build(lastName = EMPTY_STRING)

        fun input1WithShortLastName(): CreateUserInput = build(lastName = "J")

        fun input1WithLongLastName(): CreateUserInput = build(lastName = LONG_LAST_NAME_1)

        fun input1WithEmptyEmail(): CreateUserInput = build(email = EMPTY_STRING)

        fun input1WithShortEmail(): CreateUserInput = build(email = "a")

        fun input1WithLongEmail(): CreateUserInput = build(email = LONG_EMAIL_1)

        fun input1WithInvalidEmail(): CreateUserInput = build(email = INVALID_EMAIL_1)

        fun input1WithInvalidConfirmPassword(): CreateUserInput = build(confirmPassword = INVALID_CONFIRM_PASSWORD)

        private fun build(
            username: String = USERNAME_1,
            password: String = PASSWORD,
            confirmPassword: String = PASSWORD,
            firstName: String = FIRST_NAME_1,
            lastName: String = LAST_NAME_1,
            email: String = EMAIL_1,
        ): CreateUserInput =
            CreateUserInput(
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

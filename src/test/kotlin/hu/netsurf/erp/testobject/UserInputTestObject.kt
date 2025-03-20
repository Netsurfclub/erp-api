package hu.netsurf.erp.testobject

import hu.netsurf.erp.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.UserTestConstants.EMAIL_1
import hu.netsurf.erp.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.UserTestConstants.INVALID_CONFIRM_PASSWORD
import hu.netsurf.erp.UserTestConstants.INVALID_EMAIL_1
import hu.netsurf.erp.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.UserTestConstants.LONG_EMAIL_1
import hu.netsurf.erp.UserTestConstants.LONG_FIRST_NAME_1
import hu.netsurf.erp.UserTestConstants.LONG_LAST_NAME_1
import hu.netsurf.erp.UserTestConstants.LONG_PASSWORD
import hu.netsurf.erp.UserTestConstants.LONG_USERNAME_1
import hu.netsurf.erp.UserTestConstants.PASSWORD
import hu.netsurf.erp.UserTestConstants.USERNAME_1
import hu.netsurf.erp.input.UserInput

class UserInputTestObject {
    companion object {
        fun userInput1(): UserInput = build()

        fun userInput1WithEmptyUsername(): UserInput = build(username = EMPTY_STRING)

        fun userInput1WithShortUsername(): UserInput = build(username = "j")

        fun userInput1WithLongUsername(): UserInput = build(username = LONG_USERNAME_1)

        fun userInput1WithEmptyPassword(): UserInput = build(password = EMPTY_STRING)

        fun userInput1WithShortPassword(): UserInput = build(password = "p")

        fun userInput1WithLongPassword(): UserInput = build(password = LONG_PASSWORD)

        fun userInput1WithEmptyConfirmPassword(): UserInput = build(confirmPassword = EMPTY_STRING)

        fun userInput1WithEmptyFirstName(): UserInput = build(firstName = EMPTY_STRING)

        fun userInput1WithShortFirstName(): UserInput = build(firstName = "B")

        fun userInput1WithLongFirstName(): UserInput = build(firstName = LONG_FIRST_NAME_1)

        fun userInput1WithEmptyLastName(): UserInput = build(lastName = EMPTY_STRING)

        fun userInput1WithShortLastName(): UserInput = build(lastName = "J")

        fun userInput1WithLongLastName(): UserInput = build(lastName = LONG_LAST_NAME_1)

        fun userInput1WithEmptyEmail(): UserInput = build(email = EMPTY_STRING)

        fun userInput1WithShortEmail(): UserInput = build(email = "a")

        fun userInput1WithLongEmail(): UserInput = build(email = LONG_EMAIL_1)

        fun userInput1WithInvalidEmail(): UserInput = build(email = INVALID_EMAIL_1)

        fun userInput1WithInvalidConfirmPassword(): UserInput = build(confirmPassword = INVALID_CONFIRM_PASSWORD)

        private fun build(
            username: String = USERNAME_1,
            password: String = PASSWORD,
            confirmPassword: String = PASSWORD,
            firstName: String = FIRST_NAME_1,
            lastName: String = LAST_NAME_1,
            email: String = EMAIL_1,
        ): UserInput =
            UserInput(
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

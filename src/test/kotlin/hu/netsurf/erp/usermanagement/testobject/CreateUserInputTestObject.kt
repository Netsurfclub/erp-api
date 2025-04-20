package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_EMAIL
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_FIRST_NAME
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_INVALID_EMAIL
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LAST_NAME
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LONG_EMAIL
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LONG_FIRST_NAME
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LONG_LAST_NAME
import hu.netsurf.erp.usermanagement.input.CreateUserInput

class CreateUserInputTestObject {
    companion object {
        fun input1(): CreateUserInput = build()

        fun input1WithEmptyFirstName(): CreateUserInput = build(firstName = EMPTY_STRING)

        fun input1WithShortFirstName(): CreateUserInput = build(firstName = "B")

        fun input1WithLongFirstName(): CreateUserInput = build(firstName = USER_1_LONG_FIRST_NAME)

        fun input1WithEmptyLastName(): CreateUserInput = build(lastName = EMPTY_STRING)

        fun input1WithShortLastName(): CreateUserInput = build(lastName = "J")

        fun input1WithLongLastName(): CreateUserInput = build(lastName = USER_1_LONG_LAST_NAME)

        fun input1WithEmptyEmail(): CreateUserInput = build(email = EMPTY_STRING)

        fun input1WithShortEmail(): CreateUserInput = build(email = "a")

        fun input1WithLongEmail(): CreateUserInput = build(email = USER_1_LONG_EMAIL)

        fun input1WithInvalidEmail(): CreateUserInput = build(email = USER_1_INVALID_EMAIL)

        private fun build(
            firstName: String = USER_1_FIRST_NAME,
            lastName: String = USER_1_LAST_NAME,
            email: String = USER_1_EMAIL,
        ): CreateUserInput =
            CreateUserInput(
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

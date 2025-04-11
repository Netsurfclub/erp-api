package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LONG_LAST_NAME_1
import hu.netsurf.erp.usermanagement.input.CreateUserInput

class CreateUserInputTestObject {
    companion object {
        fun input1(): CreateUserInput = build()

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

        private fun build(
            firstName: String = FIRST_NAME_1,
            lastName: String = LAST_NAME_1,
            email: String = EMAIL_1,
        ): CreateUserInput =
            CreateUserInput(
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

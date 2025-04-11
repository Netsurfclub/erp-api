package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.INVALID_CONFIRM_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.LONG_USERNAME_1
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.input.CreateProfileInput

class CreateProfileInputTestObject {
    companion object {
        fun input1(): CreateProfileInput = build()

        fun input1WithEmptyUsername(): CreateProfileInput = build(username = EMPTY_STRING)

        fun input1WithShortUsername(): CreateProfileInput = build(username = "j")

        fun input1WithLongUsername(): CreateProfileInput = build(username = LONG_USERNAME_1)

        fun input1WithEmptyPassword(): CreateProfileInput = build(password = EMPTY_STRING)

        fun input1WithEmptyConfirmPassword(): CreateProfileInput = build(confirmPassword = EMPTY_STRING)

        fun input1WithInvalidConfirmPassword(): CreateProfileInput = build(confirmPassword = INVALID_CONFIRM_PASSWORD)

        private fun build(
            userId: Int = 1,
            username: String = USERNAME_1,
            password: String = PASSWORD,
            confirmPassword: String = PASSWORD,
        ): CreateProfileInput =
            CreateProfileInput(
                userId = userId,
                username = username,
                password = password,
                confirmPassword = confirmPassword,
            )
    }
}

package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_INVALID_CONFIRM_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_LONG_USERNAME
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_USERNAME
import hu.netsurf.erp.usermanagement.input.CreateProfileInput

class CreateProfileInputTestObject {
    companion object {
        fun input1(): CreateProfileInput = build()

        fun input1WithEmptyUsername(): CreateProfileInput = build(username = EMPTY_STRING)

        fun input1WithShortUsername(): CreateProfileInput = build(username = "j")

        fun input1WithLongUsername(): CreateProfileInput = build(username = PROFILE_1_LONG_USERNAME)

        fun input1WithEmptyPassword(): CreateProfileInput = build(password = EMPTY_STRING)

        fun input1WithEmptyConfirmPassword(): CreateProfileInput = build(confirmPassword = EMPTY_STRING)

        fun input1WithInvalidConfirmPassword(): CreateProfileInput = build(confirmPassword = PROFILE_1_INVALID_CONFIRM_PASSWORD)

        private fun build(
            userId: Int = 1,
            username: String = PROFILE_1_USERNAME,
            password: String = PROFILE_1_PASSWORD,
            confirmPassword: String = PROFILE_1_PASSWORD,
        ): CreateProfileInput =
            CreateProfileInput(
                userId = userId,
                username = username,
                password = password,
                confirmPassword = confirmPassword,
            )
    }
}

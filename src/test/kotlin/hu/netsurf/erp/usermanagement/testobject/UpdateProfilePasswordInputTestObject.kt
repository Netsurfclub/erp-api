package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_INVALID_NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput

class UpdateProfilePasswordInputTestObject {
    companion object {
        fun input1(): UpdateProfilePasswordInput = build()

        fun input1WithEmptyCurrentPassword(): UpdateProfilePasswordInput = build(currentPassword = EMPTY_STRING)

        fun input1WithEmptyNewPassword(): UpdateProfilePasswordInput = build(newPassword = EMPTY_STRING)

        fun input1WithEmptyConfirmNewPassword(): UpdateProfilePasswordInput = build(confirmNewPassword = EMPTY_STRING)

        fun input1WithInvalidConfirmNewPassword(): UpdateProfilePasswordInput = build(confirmNewPassword = PROFILE_1_INVALID_NEW_PASSWORD)

        private fun build(
            id: Long = 1,
            currentPassword: String = PROFILE_1_PASSWORD,
            newPassword: String = PROFILE_1_NEW_PASSWORD,
            confirmNewPassword: String = PROFILE_1_NEW_PASSWORD,
        ): UpdateProfilePasswordInput =
            UpdateProfilePasswordInput(
                id = id,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )
    }
}

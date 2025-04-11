package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput

class UpdateProfilePasswordInputTestObject {
    companion object {
        fun input1(): UpdateProfilePasswordInput = build()

        fun input1WithEmptyCurrentPassword(): UpdateProfilePasswordInput = build(currentPassword = EMPTY_STRING)

        fun input1WithEmptyNewPassword(): UpdateProfilePasswordInput = build(newPassword = EMPTY_STRING)

        fun input1WithEmptyConfirmNewPassword(): UpdateProfilePasswordInput = build(confirmNewPassword = EMPTY_STRING)

        fun input1WithInvalidConfirmNewPassword(): UpdateProfilePasswordInput = build(confirmNewPassword = INVALID_NEW_PASSWORD)

        private fun build(
            id: Int = 1,
            currentPassword: String = PASSWORD,
            newPassword: String = NEW_PASSWORD,
            confirmNewPassword: String = NEW_PASSWORD,
        ): UpdateProfilePasswordInput =
            UpdateProfilePasswordInput(
                id = id,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )
    }
}

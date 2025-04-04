package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.common.constant.CommonTestConstants.EMPTY_STRING
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_CURRENT_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.INVALID_NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput

class UpdateUserPasswordInputTestObject {
    companion object {
        fun updateUserPasswordInput1(): UpdateUserPasswordInput = build()

        fun userInput1WithEmptyCurrentPassword(): UpdateUserPasswordInput = build(currentPassword = EMPTY_STRING)

        fun userInput1WithEmptyNewPassword(): UpdateUserPasswordInput = build(newPassword = EMPTY_STRING)

        fun userInput1WithEmptyConfirmNewPassword(): UpdateUserPasswordInput = build(confirmNewPassword = EMPTY_STRING)

        fun updateUserPasswordInput1WithInvalidCurrentPassword(): UpdateUserPasswordInput =
            build(currentPassword = INVALID_CURRENT_PASSWORD)

        fun updateUserPasswordInput1WithNewPasswordAndPasswordInDatabaseMatches(): UpdateUserPasswordInput = build(newPassword = PASSWORD)

        fun updateUserPasswordInput1WithInvalidConfirmNewPassword(): UpdateUserPasswordInput =
            build(confirmNewPassword = INVALID_NEW_PASSWORD)

        private fun build(
            userId: Int = 1,
            currentPassword: String = PASSWORD,
            newPassword: String = NEW_PASSWORD,
            confirmNewPassword: String = NEW_PASSWORD,
        ): UpdateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = userId,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )
    }
}

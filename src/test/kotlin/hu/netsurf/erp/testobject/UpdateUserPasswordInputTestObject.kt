package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput

class UpdateUserPasswordInputTestObject {
    companion object {
        fun updateUserPasswordInput1(): UpdateUserPasswordInput = build()

        fun updateUserPasswordInput1WithInvalidCurrentPassword(): UpdateUserPasswordInput = build(currentPassword = "pAsSwOrD1")

        fun updateUserPasswordInput1WithInvalidConfirmNewPassword(): UpdateUserPasswordInput = build(confirmNewPassword = "NeWpAsSwOrD1")

        private fun build(
            userId: Int = 1,
            currentPassword: String = "pAsSwOrD",
            newPassword: String = "NeWpAsSwOrD",
            confirmNewPassword: String = "NeWpAsSwOrD",
        ): UpdateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = userId,
                currentPassword = currentPassword,
                newPassword = newPassword,
                confirmNewPassword = confirmNewPassword,
            )
    }
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput

class UpdateUserPasswordInputTestObject {
    companion object {
        fun updateUserPasswordInput1(): UpdateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = 1,
                currentPassword = "pAsSwOrD",
                newPassword = "NeWpAsSwOrD",
                confirmNewPassword = "NeWpAsSwOrD",
            )

        fun updateUserPasswordInput1WithInvalidPassword(): UpdateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = 1,
                currentPassword = "pAsSwOrD1",
                newPassword = "NeWpAsSwOrD",
                confirmNewPassword = "NeWpAsSwOrD",
            )

        fun updateUserPasswordInput1WithInvalidNewPassword(): UpdateUserPasswordInput =
            UpdateUserPasswordInput(
                userId = 1,
                currentPassword = "pAsSwOrD",
                newPassword = "NeWpAsSwOrD",
                confirmNewPassword = "NeWpAsSwOrD1",
            )
    }
}

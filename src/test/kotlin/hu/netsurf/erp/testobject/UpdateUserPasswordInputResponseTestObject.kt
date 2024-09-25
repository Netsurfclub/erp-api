package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInputResponse
import hu.netsurf.erp.usermanagement.model.User

class UpdateUserPasswordInputResponseTestObject {
    companion object {
        fun updateUserPasswordInputResponse1(): UpdateUserPasswordInputResponse = build()

        private fun build(
            user: User = UserTestObject.user1(),
            errorMessage: String = "",
        ): UpdateUserPasswordInputResponse =
            UpdateUserPasswordInputResponse(
                user = user,
                errorMessage = errorMessage,
            )
    }
}

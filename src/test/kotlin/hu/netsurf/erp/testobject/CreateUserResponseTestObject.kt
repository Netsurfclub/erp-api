package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.CreateUserResponse
import hu.netsurf.erp.usermanagement.model.User

class CreateUserResponseTestObject {
    companion object {
        fun createUserRequest1(): CreateUserResponse = build()

        private fun build(
            user: User = UserTestObject.user1(),
            errorMessage: String = "",
        ): CreateUserResponse =
            CreateUserResponse(
                user = user,
                errorMessage = errorMessage,
            )
    }
}

package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.CreateUserRequest
import hu.netsurf.erp.usermanagement.model.UserInput

class CreateUserRequestTestObject {
    companion object {
        fun createUserRequest1(): CreateUserRequest = build()

        private fun build(userInput: UserInput = UserInputTestObject.userInput1()): CreateUserRequest = CreateUserRequest(input = userInput)
    }
}

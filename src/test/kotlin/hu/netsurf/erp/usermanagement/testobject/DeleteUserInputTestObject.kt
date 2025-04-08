package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.input.DeleteUserInput

class DeleteUserInputTestObject {
    companion object {
        fun input1(): DeleteUserInput = build()

        private fun build(userId: Int = 1): DeleteUserInput = DeleteUserInput(userId = userId)
    }
}

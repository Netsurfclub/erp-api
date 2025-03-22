package hu.netsurf.erp.testobject

import hu.netsurf.erp.input.DeleteUserInput

class DeleteUserInputTestObject {
    companion object {
        fun deleteUserInput1(): DeleteUserInput = build()

        private fun build(userId: Int = 1): DeleteUserInput = DeleteUserInput(userId = userId)
    }
}

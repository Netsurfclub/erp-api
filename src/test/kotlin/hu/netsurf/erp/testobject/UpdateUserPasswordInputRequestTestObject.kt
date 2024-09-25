package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInputRequest

class UpdateUserPasswordInputRequestTestObject {
    companion object {
        fun updateUserPasswordInputRequest1(): UpdateUserPasswordInputRequest = build()

        private fun build(
            updateUserPasswordInput: UpdateUserPasswordInput = UpdateUserPasswordInputTestObject.updateUserPasswordInput1(),
        ): UpdateUserPasswordInputRequest =
            UpdateUserPasswordInputRequest(
                input = updateUserPasswordInput,
            )
    }
}

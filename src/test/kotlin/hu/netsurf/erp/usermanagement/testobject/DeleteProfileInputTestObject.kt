package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.input.DeleteProfileInput

class DeleteProfileInputTestObject {
    companion object {
        fun input1(): DeleteProfileInput = build()

        private fun build(userId: Long = 1): DeleteProfileInput = DeleteProfileInput(userId = userId)
    }
}

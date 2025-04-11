package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.model.User

class UserTestObject {
    companion object {
        fun user1(): User = build()

        private fun build(
            id: Int = 1,
            firstName: String = FIRST_NAME_1,
            lastName: String = LAST_NAME_1,
            email: String = EMAIL_1,
        ): User =
            User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

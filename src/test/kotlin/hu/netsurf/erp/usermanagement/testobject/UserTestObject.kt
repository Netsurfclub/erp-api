package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_EMAIL
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_FIRST_NAME
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USER_1_LAST_NAME
import hu.netsurf.erp.usermanagement.model.User

class UserTestObject {
    companion object {
        fun user1(): User = build()

        private fun build(
            id: Int = 1,
            firstName: String = USER_1_FIRST_NAME,
            lastName: String = USER_1_LAST_NAME,
            email: String = USER_1_EMAIL,
        ): User =
            User(
                id = id,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

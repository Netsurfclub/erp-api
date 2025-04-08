package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.EMAIL_2
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.FIRST_NAME_2
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.LAST_NAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.USERNAME_2
import hu.netsurf.erp.usermanagement.model.User

class UserTestObject {
    companion object {
        fun user1(): User = build()

        fun user1IsDeleted(): User = build(isDeleted = true)

        fun user2(): User =
            build(
                id = 2,
                username = USERNAME_2,
                firstName = FIRST_NAME_2,
                email = EMAIL_2,
            )

        private fun build(
            id: Int = 1,
            username: String = USERNAME_1,
            password: String = PASSWORD,
            firstName: String = FIRST_NAME_1,
            lastName: String = LAST_NAME_1,
            email: String = EMAIL_1,
            isDeleted: Boolean = false,
        ): User =
            User(
                id = id,
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName,
                email = email,
                isDeleted = isDeleted,
            )
    }
}

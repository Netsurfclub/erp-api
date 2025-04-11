package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.USERNAME_1
import hu.netsurf.erp.usermanagement.model.Profile

class ProfileTestObject {
    companion object {
        fun profile1(): Profile = build()

        fun profile1IsDeleted(): Profile = build(isDeleted = true)

        private fun build(
            id: Int = 1,
            username: String = USERNAME_1,
            password: String = HASHED_PASSWORD,
            isDeleted: Boolean = false,
        ): Profile =
            Profile(
                id = id,
                username = username,
                password = password,
                isDeleted = isDeleted,
            )
    }
}

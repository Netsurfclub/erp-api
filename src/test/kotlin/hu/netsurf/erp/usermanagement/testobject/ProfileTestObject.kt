package hu.netsurf.erp.usermanagement.testobject

import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_USERNAME
import hu.netsurf.erp.usermanagement.model.Profile

class ProfileTestObject {
    companion object {
        fun profile1(): Profile = build()

        fun profile1IsDeleted(): Profile = build(isDeleted = true)

        private fun build(
            id: Long = 1,
            username: String = PROFILE_1_USERNAME,
            password: String = PROFILE_1_HASHED_PASSWORD,
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

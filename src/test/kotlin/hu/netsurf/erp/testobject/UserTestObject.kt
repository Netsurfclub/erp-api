package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.User

class UserTestObject {
    companion object {
        fun user1(): User = build()

        fun user2(): User =
            build(
                id = 2,
                username = "jgabor",
                firstName = "Gábor",
                email = "gjuhasz@netsurfclub.hu",
            )

        private fun build(
            id: Int = 1,
            username: String = "jbence",
            password: String = "pAsSwOrD",
            firstName: String = "Bence",
            lastName: String = "Juhász",
            email: String = "bjuhasz@netsurfclub.hu",
        ): User =
            User(
                id = id,
                username = username,
                password = password,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

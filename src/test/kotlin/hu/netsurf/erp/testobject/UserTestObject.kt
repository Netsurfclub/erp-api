package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.User

class UserTestObject {
    companion object {
        fun user1(): User =
            User(
                id = 1,
                username = "jbence",
                password = "pAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "bjuhasznetsurfclub.hu",
            )

        fun user2(): User =
            User(
                id = 2,
                username = "jgabor",
                password = "pAsSwOrD",
                firstName = "Gábor",
                lastName = "Juhász",
                email = "gjuhasznetsurfclub.hu",
            )
    }
}

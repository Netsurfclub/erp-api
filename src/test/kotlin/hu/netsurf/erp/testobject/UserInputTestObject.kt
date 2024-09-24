package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UserInput

class UserInputTestObject {
    companion object {
        fun userInput1(): UserInput = build()

        private fun build(
            username: String = "jbence",
            password: String = "pAsSwOrD",
            confirmPassword: String = "pAsSwOrD",
            firstName: String = "Bence",
            lastName: String = "Juhász",
            email: String = "bjuhasz@netsurfclub.hu",
        ): UserInput =
            UserInput(
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                firstName = firstName,
                lastName = lastName,
                email = email,
            )
    }
}

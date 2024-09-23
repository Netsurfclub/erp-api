package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UserInput

class UserInputTestObject {
    companion object {
        fun userInput1(): UserInput =
            UserInput(
                username = "jbence",
                password = "pAsSwOrD",
                confirmPassword = "pAsSwOrD",
                firstName = "Bence",
                lastName = "Juhász",
                email = "bjuhasz@netsurfclub.hu",
            )
    }
}

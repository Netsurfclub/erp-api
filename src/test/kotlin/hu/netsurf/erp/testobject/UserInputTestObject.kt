package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UserInput

class UserInputTestObject {
    companion object {
        fun userInput1(): UserInput = build()

        fun userInput1WithEmptyUsername(): UserInput = build(username = "")

        fun userInput1WithEmptyPassword(): UserInput = build(password = "")

        fun userInput1WithEmptyConfirmPassword(): UserInput = build(confirmPassword = "")

        fun userInput1WithEmptyFirstName(): UserInput = build(firstName = "")

        fun userInput1WithEmptyLastName(): UserInput = build(lastName = "")

        fun userInput1WithEmptyEmail(): UserInput = build(email = "")

        fun userInput1WithInvalidEmail(): UserInput = build(email = "bjuhasznetsurfclub.hu")

        fun userInput1WithInvalidConfirmPassword(): UserInput = build(confirmPassword = "CoNfIrMpAsSwOrD")

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

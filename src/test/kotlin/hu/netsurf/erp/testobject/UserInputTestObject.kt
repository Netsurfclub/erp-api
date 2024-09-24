package hu.netsurf.erp.testobject

import hu.netsurf.erp.usermanagement.model.UserInput

class UserInputTestObject {
    companion object {
        fun userInput1(): UserInput = build()

        fun userInput1WithEmptyUsername(): UserInput = build(username = "")

        fun userInput1WithShortUsername(): UserInput = build(username = "j")

        fun userInput1WithLongUsername(): UserInput =
            build(
                username =
                    "jbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbence" +
                        "jbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbence" +
                        "jbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejben",
            )

        fun userInput1WithEmptyPassword(): UserInput = build(password = "")

        fun userInput1WithShortPassword(): UserInput = build(password = "p")

        fun userInput1WithLongPassword(): UserInput = build(password = "pAsSwOrDpAsSwOrD")

        fun userInput1WithEmptyConfirmPassword(): UserInput = build(confirmPassword = "")

        fun userInput1WithEmptyFirstName(): UserInput = build(firstName = "")

        fun userInput1WithShortFirstName(): UserInput = build(firstName = "B")

        fun userInput1WithLongFirstName(): UserInput =
            build(
                firstName =
                    "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
                        "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
                        "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceB",
            )

        fun userInput1WithEmptyLastName(): UserInput = build(lastName = "")

        fun userInput1WithShortLastName(): UserInput = build(lastName = "J")

        fun userInput1WithLongLastName(): UserInput =
            build(
                lastName =
                    "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
                        "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
                        "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhá",
            )

        fun userInput1WithEmptyEmail(): UserInput = build(email = "")

        fun userInput1WithShortEmail(): UserInput = build(email = "a")

        fun userInput1WithLongEmail(): UserInput =
            build(
                email = "bjuhasz@netsurfclub.hubjuhasz@netsurfclub.hubjuhasz@netsurfclub.hubjuhasz@ne",
            )

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

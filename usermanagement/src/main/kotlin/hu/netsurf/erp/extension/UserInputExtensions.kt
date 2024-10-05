package hu.netsurf.erp.extension

import hu.netsurf.erp.input.UserInput
import hu.netsurf.erp.model.User

fun UserInput.toUser(): User =
    User(
        username = this.username,
        password = this.password,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )

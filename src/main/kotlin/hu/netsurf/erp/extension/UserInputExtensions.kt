package hu.netsurf.erp.extension

import hu.netsurf.erp.model.User
import hu.netsurf.erp.model.UserInput

fun UserInput.toUser(): User =
    User(
        username = this.username,
        password = this.password,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )

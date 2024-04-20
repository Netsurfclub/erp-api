package hu.netsurf.erp.usermanagement.extension

import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.model.UserInput

fun UserInput.toUser(): User {
    return User(
        username = this.username,
        password = this.password,
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email,
    )
}

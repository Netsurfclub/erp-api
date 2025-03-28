package hu.netsurf.erp.usermanagement.util

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class PasswordUtil(
    private val passwordEncoder: PasswordEncoder,
) {
    fun encode(rawPassword: String): String = passwordEncoder.encode(rawPassword)

    // For login.

    /*fun verify(
        rawPassword: String,
        hashedPassword: String,
    ): Boolean = passwordEncoder.matches(rawPassword, hashedPassword)*/
}

package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.password.PasswordEncoder

class PasswordUtilTests {
    private val passwordEncoder: PasswordEncoder = mockk()
    private val passwordUtil: PasswordUtil = PasswordUtil(passwordEncoder)

    @Test
    fun `encode test`() {
        coEvery {
            passwordEncoder.encode(any())
        } returns HASHED_PASSWORD

        val result = passwordUtil.encode(PASSWORD)
        assertEquals(HASHED_PASSWORD, result)
    }
}

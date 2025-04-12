package hu.netsurf.erp.usermanagement.util.password

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun `verify test happy path`() {
        coEvery {
            passwordEncoder.matches(any(), any())
        } returns true

        val result = passwordUtil.verify(PASSWORD, HASHED_PASSWORD)
        assertTrue(result)
    }

    @Test
    fun `verify test unhappy path`() {
        coEvery {
            passwordEncoder.matches(any(), any())
        } returns false

        val result = passwordUtil.verify(PASSWORD, HASHED_PASSWORD)
        assertFalse(result)
    }
}

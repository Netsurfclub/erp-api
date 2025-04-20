package hu.netsurf.erp.usermanagement.util.password

import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
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
        } returns PROFILE_1_HASHED_PASSWORD

        val result = passwordUtil.encode(PROFILE_1_PASSWORD)
        assertEquals(PROFILE_1_HASHED_PASSWORD, result)
    }

    @Test
    fun `verify test happy path`() {
        coEvery {
            passwordEncoder.matches(any(), any())
        } returns true

        val result = passwordUtil.verify(PROFILE_1_PASSWORD, PROFILE_1_HASHED_PASSWORD)
        assertTrue(result)
    }

    @Test
    fun `verify test unhappy path`() {
        coEvery {
            passwordEncoder.matches(any(), any())
        } returns false

        val result = passwordUtil.verify(PROFILE_1_PASSWORD, PROFILE_1_HASHED_PASSWORD)
        assertFalse(result)
    }
}

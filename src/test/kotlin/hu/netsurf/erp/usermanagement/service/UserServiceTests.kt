package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.repository.UserRepository
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1IsDeleted
import hu.netsurf.erp.usermanagement.util.PasswordUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class UserServiceTests {
    private val userRepository: UserRepository = mockk()
    private val passwordUtil: PasswordUtil = mockk()
    private val userService: UserService = UserService(userRepository, passwordUtil)
    private val currentAndNewPassword: Pair<String, String> = Pair(PASSWORD, NEW_PASSWORD)

    @Test
    fun `getUser test happy path`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(user1())

        val result = userService.getUser(1)
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `getUser test unhappy path - user not found by id`() {
        every {
            userRepository.findById(any())
        } returns Optional.empty()

        assertThrows<UserNotFoundException> {
            userService.getUser(3)
        }
    }

    @Test
    fun `createUser test happy path`() {
        every {
            passwordUtil.encode(any())
        } returns HASHED_PASSWORD
        every {
            userRepository.save(any())
        } returns user1()

        val result = userService.createUser(user1())
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        coEvery {
            userRepository.findById(any())
        } returns Optional.of(user1())
        every {
            passwordUtil.verify(any(), any())
        } returns true
        every {
            passwordUtil.encode(any())
        } returns HASHED_PASSWORD
        every {
            userRepository.save(any())
        } returns user1()

        val result = userService.updateUserPassword(1, currentAndNewPassword)
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `updateUserPassword test unhappy path - user is already deleted (not found in database)`() {
        coEvery {
            userRepository.findById(any())
        } returns Optional.of(user1IsDeleted())

        assertThrows<UserNotFoundException> {
            userService.updateUserPassword(1, currentAndNewPassword)
        }
    }

    @Test
    fun `updateUserPassword test unhappy path - current password and password in database not matches`() {
        coEvery {
            userRepository.findById(any())
        } returns Optional.of(user1())
        coEvery { passwordUtil.verify(any(), any()) } returns false

        assertThrows<CurrentPasswordAndPasswordInDatabaseNotMatchesException> {
            userService.updateUserPassword(1, currentAndNewPassword)
        }
    }

    @Test
    fun `deleteUser test happy path`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(user1())
        every {
            userRepository.save(any())
        } returns user1IsDeleted()

        val result = userService.deleteUser(1)
        assertNotNull(result)
        assertEquals(user1IsDeleted(), result)
    }

    @Test
    fun `deleteUser test unhappy path - user is already deleted (not found in database)`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(user1IsDeleted())

        assertThrows<UserNotFoundException> {
            userService.deleteUser(1)
        }
    }
}

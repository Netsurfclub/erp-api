package hu.netsurf.erp.service

import hu.netsurf.erp.exception.UserNotFoundException
import hu.netsurf.erp.repository.UserRepository
import hu.netsurf.erp.testobject.UserTestObject.Companion.user1
import hu.netsurf.erp.testobject.UserTestObject.Companion.user2
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class UserServiceTests {
    private val userRepository: UserRepository = mockk()
    private val userService: UserService = UserService(userRepository)

    @Test
    fun `getUsers test happy path`() {
        every {
            userRepository.findAll()
        } returns listOf(user1(), user2())

        val result = userService.getUsers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getUser test happy path`() {
        every {
            userRepository.findById(1)
        } returns Optional.of(user1())

        val result = userService.getUser(1)
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `getUser test unhappy path - user is empty`() {
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
            userRepository.save(any())
        } returns user1()

        val result = userService.createUser(user1())
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `updateUser test happy path`() {
        every {
            userRepository.save(any())
        } returns user1()

        val result = userService.updateUser(user1())
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `deleteUser test happy path`() {
        every {
            userRepository.findById(1)
        } returns Optional.of(user1())
        justRun { userRepository.deleteById(any()) }

        val result = userService.deleteUser(1)
        assertNotNull(result)
        assertEquals(user1(), result)
    }
}

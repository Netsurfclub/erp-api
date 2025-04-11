package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.repository.UserRepository
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class UserServiceTests {
    private val userRepository: UserRepository = mockk()
    private val userService: UserService = UserService(userRepository)

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
            userRepository.save(any())
        } returns user1()

        val result = userService.createUser(user1())
        assertNotNull(result)
        assertEquals(user1(), result)
    }
}

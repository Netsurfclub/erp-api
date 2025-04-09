package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.usermanagement.constant.UserTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.NEW_PASSWORD
import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.repository.UserRepository
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1IsDeleted
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user2
import hu.netsurf.erp.usermanagement.util.PasswordUtil
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.Optional
import java.util.stream.Stream

class UserServiceTests {
    private val userRepository: UserRepository = mockk()
    private val passwordUtil: PasswordUtil = mockk()
    private val userService: UserService = UserService(userRepository, passwordUtil)

    companion object {
        @JvmStatic
        fun getUsersParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("2 active users", listOf(user1(), user2()), 2),
                Arguments.of("1 deleted user, 1 active user", listOf(user1IsDeleted(), user2()), 1),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("getUsersParams")
    fun `getUsers test happy path`(
        testCase: String,
        users: List<User>,
        expectedCount: Int,
    ) {
        every {
            userRepository.findAll()
        } returns users

        val result = userService.getUsers()
        assertEquals(result.size, expectedCount)
    }

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
        every {
            passwordUtil.encode(any())
        } returns HASHED_PASSWORD
        every {
            userRepository.save(any())
        } returns user1()

        val result = userService.updateUserPassword(user1(), NEW_PASSWORD)
        assertNotNull(result)
        assertEquals(user1(), result)
    }

    @Test
    fun `updateUserPassword test unhappy path - user is already deleted (not found in database)`() {
        assertThrows<UserNotFoundException> {
            userService.updateUserPassword(user1IsDeleted(), NEW_PASSWORD)
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

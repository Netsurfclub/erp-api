package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject
import hu.netsurf.erp.testobject.UserInputTestObject
import hu.netsurf.erp.testobject.UserTestObject
import hu.netsurf.erp.usermanagement.exception.ConfirmCurrentPasswordException
import hu.netsurf.erp.usermanagement.exception.ConfirmNewPasswordException
import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.repository.UserRepository
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputSanitizer
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputValidator
import hu.netsurf.erp.usermanagement.util.UserInputSanitizer
import hu.netsurf.erp.usermanagement.util.UserInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class UserServiceTests {
    private val userRepository: UserRepository = mockk()
    private val userInputSanitizer: UserInputSanitizer = mockk()
    private val userInputValidator: UserInputValidator = mockk()
    private val updateUserPasswordInputSanitizer: UpdateUserPasswordInputSanitizer = mockk()
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator = mockk()
    private val userService: UserService =
        UserService(
            userRepository,
            userInputSanitizer,
            userInputValidator,
            updateUserPasswordInputSanitizer,
            updateUserPasswordInputValidator,
        )

    @BeforeEach
    fun setup() {
        every {
            updateUserPasswordInputSanitizer.sanitize(any())
        } returns UpdateUserPasswordInputTestObject.updateUserPasswordInput1()
    }

    @Test
    fun `getUsers test happy path`() {
        every {
            userRepository.findAll()
        } returns listOf(UserTestObject.user1(), UserTestObject.user2())

        val result = userService.getUsers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createUser test happy path`() {
        every {
            userInputSanitizer.sanitize(any())
        } returns UserInputTestObject.userInput1()
        justRun { userInputValidator.validate(any()) }
        every {
            userRepository.save(any())
        } returns UserTestObject.user1()

        val result = userService.createUser(UserInputTestObject.userInput1())
        assertEquals(UserTestObject.user1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(UserTestObject.user1())
        every {
            userRepository.save(any())
        } returns UserTestObject.user1()
        justRun { updateUserPasswordInputValidator.validate(any(), any()) }

        val result = userService.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1())
        assertEquals(UserTestObject.user1(), result)
    }

    @Test
    fun `updateUserPassword test unhappy path - user is empty`() {
        every {
            userRepository.findById(any())
        } returns Optional.empty()

        assertThrows<UserNotFoundException> {
            userService.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1())
        }
    }

    @Test
    fun `updateUserPassword test unhappy path - user current password not matches with the one in database`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(UserTestObject.user1())
        every {
            updateUserPasswordInputValidator.validate(any(), any())
        } throws ConfirmCurrentPasswordException()

        assertThrows<ConfirmCurrentPasswordException> {
            userService.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidCurrentPassword())
        }
    }

    @Test
    fun `updateUserPassword test unhappy path - new password and confirm password not matches`() {
        every {
            userRepository.findById(any())
        } returns Optional.of(UserTestObject.user1())
        every {
            updateUserPasswordInputValidator.validate(any(), any())
        } throws ConfirmNewPasswordException()

        assertThrows<ConfirmNewPasswordException> {
            userService.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidConfirmNewPassword())
        }
    }
}
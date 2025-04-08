package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.usermanagement.service.UserService
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user2
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputSanitizer
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputValidator
import hu.netsurf.erp.usermanagement.util.UserInputSanitizer
import hu.netsurf.erp.usermanagement.util.UserInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1 as createUserInput1
import hu.netsurf.erp.usermanagement.testobject.DeleteUserInputTestObject.Companion.input1 as deleteUserInput1
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1 as updateUserPasswordInput1

class UserControllerTests {
    private val userService: UserService = mockk()
    private val userInputSanitizer: UserInputSanitizer = mockk()
    private val userInputValidator: UserInputValidator = mockk()
    private val updateUserPasswordInputSanitizer: UpdateUserPasswordInputSanitizer = mockk()
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator = mockk()
    private val userController: UserController =
        UserController(
            userService,
            userInputSanitizer,
            userInputValidator,
            updateUserPasswordInputSanitizer,
            updateUserPasswordInputValidator,
        )

    @Test
    fun `users test happy path`() {
        every {
            userService.getUsers()
        } returns listOf(user1(), user2())

        val result = userController.users()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createUser test happy path`() {
        every {
            userInputSanitizer.sanitize(any())
        } returns createUserInput1()
        justRun { userInputValidator.validate(any()) }
        every {
            userService.createUser(any())
        } returns user1()

        val result = userController.createUser(createUserInput1())
        assertEquals(user1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        every {
            updateUserPasswordInputSanitizer.sanitize(any())
        } returns updateUserPasswordInput1()
        every {
            userService.getUser(any())
        } returns user1()
        justRun { updateUserPasswordInputValidator.validate(any(), any()) }
        every {
            userService.updateUserPassword(any(), any())
        } returns user1()

        val result = userController.updateUserPassword(updateUserPasswordInput1())
        assertEquals(user1(), result)
    }

    @Test
    fun `deleteUser test happy path`() {
        every {
            userService.deleteUser(any())
        } returns user1()

        val result = userController.deleteUser(deleteUserInput1())
        assertEquals(user1(), result)
    }
}

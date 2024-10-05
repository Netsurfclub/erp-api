package hu.netsurf.erp.controller

import hu.netsurf.erp.service.UserService
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject
import hu.netsurf.erp.testobject.UserInputTestObject
import hu.netsurf.erp.testobject.UserTestObject
import hu.netsurf.erp.util.UpdateUserPasswordInputSanitizer
import hu.netsurf.erp.util.UpdateUserPasswordInputValidator
import hu.netsurf.erp.util.UserInputSanitizer
import hu.netsurf.erp.util.UserInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

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
        } returns listOf(UserTestObject.user1(), UserTestObject.user2())

        val result = userController.users()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createUser test happy path`() {
        every {
            userInputSanitizer.sanitize()
        } returns UserInputTestObject.userInput1()
        justRun { userInputValidator.validate() }
        every {
            userService.createUser()
        } returns UserTestObject.user1()

        val result = userController.createUser(UserInputTestObject.userInput1())
        assertEquals(UserTestObject.user1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        every {
            updateUserPasswordInputSanitizer.sanitize()
        } returns UpdateUserPasswordInputTestObject.updateUserPasswordInput1()
        every {
            userService.getUser()
        } returns UserTestObject.user1()
        justRun { updateUserPasswordInputValidator.validate() }
        every {
            userService.updateUser()
        } returns UserTestObject.user1()

        val result = userController.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1())
        assertEquals(UserTestObject.user1(), result)
    }
}

package hu.netsurf.erp.controller

import hu.netsurf.erp.service.UserService
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject
import hu.netsurf.erp.testobject.UserInputTestObject
import hu.netsurf.erp.testobject.UserTestObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class UserControllerTests {
    private val userService: UserService = mockk()
    private val userController: UserController = UserController(userService)

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
            userService.createUser(any())
        } returns UserTestObject.user1()

        val result = userController.createUser(UserInputTestObject.userInput1())
        assertEquals(UserTestObject.user1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        every {
            userService.updateUserPassword(any())
        } returns UserTestObject.user1()

        val result = userController.updateUserPassword(UpdateUserPasswordInputTestObject.updateUserPasswordInput1())
        assertEquals(UserTestObject.user1(), result)
    }
}

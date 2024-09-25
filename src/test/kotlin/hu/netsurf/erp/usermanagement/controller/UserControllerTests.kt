package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.testobject.CreateUserRequestTestObject
import hu.netsurf.erp.testobject.CreateUserResponseTestObject
import hu.netsurf.erp.testobject.UpdateUserPasswordInputRequestTestObject
import hu.netsurf.erp.testobject.UpdateUserPasswordInputResponseTestObject
import hu.netsurf.erp.testobject.UserTestObject
import hu.netsurf.erp.usermanagement.service.UserService
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

        val result = userController.createUser(CreateUserRequestTestObject.createUserRequest1())
        assertEquals(CreateUserResponseTestObject.createUserRequest1(), result)
    }

    @Test
    fun `updateUserPassword test happy path`() {
        every {
            userService.updateUserPassword(any())
        } returns UserTestObject.user1()

        val result = userController.updateUserPassword(UpdateUserPasswordInputRequestTestObject.updateUserPasswordInputRequest1())
        assertEquals(UpdateUserPasswordInputResponseTestObject.updateUserPasswordInputResponse1(), result)
    }
}

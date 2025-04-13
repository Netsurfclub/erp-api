package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.usermanagement.service.UserService
import hu.netsurf.erp.usermanagement.testobject.UserTestObject.Companion.user1
import hu.netsurf.erp.usermanagement.util.sanitization.CreateUserInputSanitizer
import hu.netsurf.erp.usermanagement.util.validation.CreateUserInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import hu.netsurf.erp.usermanagement.testobject.CreateUserInputTestObject.Companion.input1 as createUserInput1

class UserControllerTests {
    private val userService: UserService = mockk()
    private val createUserInputSanitizer: CreateUserInputSanitizer = mockk()
    private val createUserInputValidator: CreateUserInputValidator = mockk()
    private val userController: UserController =
        UserController(
            userService,
            createUserInputSanitizer,
            createUserInputValidator,
        )

    @Test
    fun `createUser test happy path`() {
        every {
            createUserInputSanitizer.sanitize(any())
        } returns createUserInput1()
        justRun { createUserInputValidator.validate(any()) }
        every {
            userService.createUser(any())
        } returns user1()

        val result = userController.createUser(createUserInput1())
        assertEquals(user1(), result)
    }
}

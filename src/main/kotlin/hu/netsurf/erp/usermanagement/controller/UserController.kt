package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.DELETE_USER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.DELETE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USERS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.USER
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.USER_INPUT
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import hu.netsurf.erp.usermanagement.input.DeleteUserInput
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.service.UserService
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputSanitizer
import hu.netsurf.erp.usermanagement.util.UpdateUserPasswordInputValidator
import hu.netsurf.erp.usermanagement.util.UserInputSanitizer
import hu.netsurf.erp.usermanagement.util.UserInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val userInputSanitizer: UserInputSanitizer,
    private val userInputValidator: UserInputValidator,
    private val updateUserPasswordInputSanitizer: UpdateUserPasswordInputSanitizer,
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @QueryMapping(name = "users")
    fun users(): List<User> {
        logger.logInfo(USERS_GRAPHQL_QUERY_RECEIVED)

        val users = userService.getUsers()

        logger.logInfo(USERS_GRAPHQL_QUERY_SUCCESS_RESPONSE)

        return users
    }

    @MutationMapping(name = "createUser")
    fun createUser(
        @Argument input: CreateUserInput,
    ): User {
        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedUserInput = userInputSanitizer.sanitize(input)
        userInputValidator.validate(sanitizedUserInput)

        val user = sanitizedUserInput.toUser()

        logger.logInfo(
            USER_INPUT_MAPPED_TO_USER,
            mapOf(
                USER_INPUT to sanitizedUserInput,
                USER to user,
            ),
        )

        val createdUser = userService.createUser(user)

        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return createdUser
    }

    @MutationMapping(name = "updateUserPassword")
    fun updateUserPassword(
        @Argument input: UpdateUserPasswordInput,
    ): User {
        logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedUserInput = updateUserPasswordInputSanitizer.sanitize(input)

        val user = userService.getUser(input.userId)

        updateUserPasswordInputValidator.validate(sanitizedUserInput, user.password)

        val updatedUser = userService.updateUserPassword(user, input.newPassword)

        logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return updatedUser
    }

    @MutationMapping(name = "deleteUser")
    fun deleteUser(
        @Argument input: DeleteUserInput,
    ): User {
        logger.logInfo(DELETE_USER_GRAPHQL_MUTATION_RECEIVED)

        val deletedUser = userService.deleteUser(input.userId)

        logger.logInfo(DELETE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return deletedUser
    }
}

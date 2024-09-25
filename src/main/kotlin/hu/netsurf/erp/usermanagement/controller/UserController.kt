package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USERS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.usermanagement.model.CreateUserRequest
import hu.netsurf.erp.usermanagement.model.CreateUserResponse
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInputRequest
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInputResponse
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
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
        @Argument request: CreateUserRequest,
    ): CreateUserResponse {
        try {
            logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_RECEIVED)

            val user = userService.createUser(request.input)

            logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

            return CreateUserResponse(user)
        } catch (exception: Exception) {
            return CreateUserResponse(errorMessage = exception.message.toString())
        }
    }

    @MutationMapping(name = "updateUserPassword")
    fun updateUserPassword(
        @Argument request: UpdateUserPasswordInputRequest,
    ): UpdateUserPasswordInputResponse {
        try {
            logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED)

            val user = userService.updateUserPassword(request.input)

            logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

            return UpdateUserPasswordInputResponse(user)
        } catch (exception: Exception) {
            return UpdateUserPasswordInputResponse(errorMessage = exception.message.toString())
        }
    }
}

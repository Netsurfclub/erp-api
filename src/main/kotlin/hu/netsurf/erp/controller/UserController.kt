package hu.netsurf.erp.controller

import hu.netsurf.erp.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.constant.LogEventConstants.USERS_GRAPHQL_QUERY_RECEIVED
import hu.netsurf.erp.constant.LogEventConstants.USERS_GRAPHQL_QUERY_SUCCESS_RESPONSE
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.model.UpdateUserPasswordInput
import hu.netsurf.erp.model.User
import hu.netsurf.erp.model.UserInput
import hu.netsurf.erp.service.UserService
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
        @Argument input: UserInput,
    ): User {
        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_RECEIVED)

        val user = userService.createUser(input)

        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return user
    }

    @MutationMapping(name = "updateUserPassword")
    fun updateUserPassword(
        @Argument input: UpdateUserPasswordInput,
    ): User {
        logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED)

        val user = userService.updateUserPassword(input)

        logger.logInfo(UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return user
    }
}
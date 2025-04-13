package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.USER
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.USER_INPUT
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.service.UserService
import hu.netsurf.erp.usermanagement.util.sanitization.CreateUserInputSanitizer
import hu.netsurf.erp.usermanagement.util.validation.CreateUserInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
    private val createUserInputSanitizer: CreateUserInputSanitizer,
    private val createUserInputValidator: CreateUserInputValidator,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @MutationMapping(name = "createUser")
    fun createUser(
        @Argument input: CreateUserInput,
    ): User {
        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedInput = createUserInputSanitizer.sanitize(input)
        createUserInputValidator.validate(sanitizedInput)

        val user = sanitizedInput.toUser()

        logger.logInfo(
            USER_INPUT_MAPPED_TO_USER,
            mapOf(
                USER_INPUT to sanitizedInput,
                USER to user,
            ),
        )

        val createdUser = userService.createUser(user)

        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return createdUser
    }
}

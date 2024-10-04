package hu.netsurf.erp.service

import hu.netsurf.erp.constant.LogEventConstants.USERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.constant.LogEventConstants.USER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_UPDATED_IN_DATABASE
import hu.netsurf.erp.constant.LoggerConstants.UPDATED_USER
import hu.netsurf.erp.constant.LoggerConstants.USER
import hu.netsurf.erp.constant.LoggerConstants.USER_INPUT
import hu.netsurf.erp.exception.UserNotFoundException
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.extension.toUser
import hu.netsurf.erp.model.UpdateUserPasswordInput
import hu.netsurf.erp.model.User
import hu.netsurf.erp.model.UserInput
import hu.netsurf.erp.repository.UserRepository
import hu.netsurf.erp.util.UpdateUserPasswordInputSanitizer
import hu.netsurf.erp.util.UpdateUserPasswordInputValidator
import hu.netsurf.erp.util.UserInputSanitizer
import hu.netsurf.erp.util.UserInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userInputSanitizer: UserInputSanitizer,
    private val userInputValidator: UserInputValidator,
    private val updateUserPasswordInputSanitizer: UpdateUserPasswordInputSanitizer,
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getUsers(): List<User> {
        logger.logInfo(USERS_RETRIEVED_FROM_DATABASE)

        return userRepository.findAll()
    }

    fun createUser(userInput: UserInput): User {
        val sanitizedUserInput = userInputSanitizer.sanitize(userInput)
        userInputValidator.validate(sanitizedUserInput)

        val user = sanitizedUserInput.toUser()

        logger.logInfo(
            USER_INPUT_MAPPED_TO_USER,
            mapOf(
                USER_INPUT to sanitizedUserInput,
                USER to user,
            ),
        )

        val savedUser = userRepository.save(user)
        return savedUser
    }

    private fun getUser(id: Int): User {
        val user = userRepository.findById(id)

        if (user.isEmpty) {
            throw UserNotFoundException(id)
        }

        logger.logInfo(
            USER_RETRIEVED_FROM_DATABASE,
            mapOf(USER to user.get()),
        )

        return user.get()
    }

    fun updateUserPassword(updateUserPasswordInput: UpdateUserPasswordInput): User {
        val sanitizedUserInput = updateUserPasswordInputSanitizer.sanitize(updateUserPasswordInput)

        val user = getUser(updateUserPasswordInput.userId)

        updateUserPasswordInputValidator.validate(sanitizedUserInput, user.password)

        user.password = updateUserPasswordInput.newPassword
        return updateUser(user)
    }

    private fun updateUser(user: User): User {
        val updatedUser = userRepository.save(user)

        logger.logInfo(
            USER_UPDATED_IN_DATABASE,
            mapOf(
                USER to user,
                UPDATED_USER to updatedUser,
            ),
        )

        return updatedUser
    }
}

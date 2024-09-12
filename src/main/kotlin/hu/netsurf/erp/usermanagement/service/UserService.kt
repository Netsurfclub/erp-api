package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USER_UPDATED_IN_DATABASE
import hu.netsurf.erp.common.logging.constant.usermanagement.LoggerConstants.UPDATED_USER
import hu.netsurf.erp.common.logging.constant.usermanagement.LoggerConstants.USER
import hu.netsurf.erp.common.logging.constant.usermanagement.LoggerConstants.USER_INPUT
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.usermanagement.exception.ConfirmCurrentPasswordException
import hu.netsurf.erp.usermanagement.exception.ConfirmNewPasswordException
import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.extension.toUser
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.model.UserInput
import hu.netsurf.erp.usermanagement.repository.UserRepository
import hu.netsurf.erp.usermanagement.util.UserInputSanitizer
import hu.netsurf.erp.usermanagement.util.UserInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userInputSanitizer: UserInputSanitizer,
    private val userInputValidator: UserInputValidator,
) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

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
        val user = getUser(updateUserPasswordInput.userId)

        // TODO: Move to validator component.
        if (updateUserPasswordInput.currentPassword != user.password) {
            throw ConfirmCurrentPasswordException()
        }

        if (updateUserPasswordInput.newPassword != updateUserPasswordInput.confirmNewPassword) {
            throw ConfirmNewPasswordException()
        }
        // TODO: ============================

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

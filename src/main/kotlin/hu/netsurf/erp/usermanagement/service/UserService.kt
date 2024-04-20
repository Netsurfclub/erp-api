package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.common.logging.constant.usermanagement.LoggerConstants.USER
import hu.netsurf.erp.common.logging.constant.usermanagement.LoggerConstants.USER_INPUT
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.usermanagement.extension.toUser
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.model.UserInput
import hu.netsurf.erp.usermanagement.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    val logger: Logger = LoggerFactory.getLogger(UserService::class.java)

    fun getUsers(): List<User> {
        logger.logInfo(USERS_RETRIEVED_FROM_DATABASE)

        return userRepository.findAll()
    }

    fun createUser(userInput: UserInput): User {
        val user = userInput.toUser()

        logger.logInfo(
            USER_INPUT_MAPPED_TO_USER,
            mapOf(
                USER_INPUT to userInput,
                USER to user,
            ),
        )

        val savedUser = userRepository.save(user)
        return savedUser
    }
}

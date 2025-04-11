package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USER_SAVED_TO_DATABASE
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.USER
import hu.netsurf.erp.usermanagement.exception.UserNotFoundException
import hu.netsurf.erp.usermanagement.model.User
import hu.netsurf.erp.usermanagement.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun createUser(user: User): User {
        val savedUser = userRepository.save(user)

        logger.logInfo(
            USER_SAVED_TO_DATABASE,
            mapOf(USER to savedUser),
        )

        return savedUser
    }

    fun getUser(id: Int): User {
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
}

package hu.netsurf.erp.service

import hu.netsurf.erp.constant.LogEventConstants.USERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_DELETED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_SAVED_TO_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.USER_UPDATED_IN_DATABASE
import hu.netsurf.erp.constant.LoggerConstants.UPDATED_USER
import hu.netsurf.erp.constant.LoggerConstants.USER
import hu.netsurf.erp.exception.UserNotFoundException
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.model.User
import hu.netsurf.erp.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getUsers(): List<User> {
        logger.logInfo(USERS_RETRIEVED_FROM_DATABASE)

        return userRepository.findAll()
    }

    fun createUser(user: User): User {
        val savedUser = userRepository.save(user)

        logger.logInfo(
            USER_SAVED_TO_DATABASE,
            mapOf(
                USER to user,
            ),
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

    fun updateUser(user: User): User {
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

    fun deleteUser(id: Int): User {
        val deletedUser = getUser(id)

        userRepository.deleteById(id)

        logger.logInfo(
            USER_DELETED_FROM_DATABASE,
            mapOf(USER to deletedUser),
        )

        return deletedUser
    }
}

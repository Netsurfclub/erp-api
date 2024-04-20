package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants.USERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.usermanagement.model.User
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
}

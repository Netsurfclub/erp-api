package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.PROFILE_DELETED_FROM_DATABASE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.PROFILE_PASSWORD_UPDATED_IN_DATABASE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.PROFILE_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.PROFILE_SAVED_TO_DATABASE
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.PROFILE
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.UPDATED_PROFILE
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.ProfileNotFoundException
import hu.netsurf.erp.usermanagement.model.Profile
import hu.netsurf.erp.usermanagement.repository.ProfileRepository
import hu.netsurf.erp.usermanagement.util.PasswordUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProfileService(
    private val profileRepository: ProfileRepository,
    private val passwordUtil: PasswordUtil,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun createProfile(profile: Profile): Profile {
        profile.password = passwordUtil.encode(profile.password)

        val savedProfile = profileRepository.save(profile)

        logger.logInfo(
            PROFILE_SAVED_TO_DATABASE,
            mapOf(PROFILE to savedProfile),
        )

        return savedProfile
    }

    fun getProfile(id: Int): Profile {
        val profile = profileRepository.findById(id)

        if (profile.isEmpty) {
            throw ProfileNotFoundException(id)
        }

        logger.logInfo(
            PROFILE_RETRIEVED_FROM_DATABASE,
            mapOf(PROFILE to profile.get()),
        )

        return profile.get()
    }

    fun updateProfilePassword(
        id: Int,
        currentAndNewPassword: Pair<String, String>,
    ): Profile {
        val profileToUpdate = getProfile(id)

        if (profileToUpdate.isDeleted) {
            throw ProfileNotFoundException(id)
        }

        val (currentPassword, newPassword) = currentAndNewPassword
        if (!passwordUtil.verify(currentPassword, profileToUpdate.password)) {
            throw CurrentPasswordAndPasswordInDatabaseNotMatchesException()
        }

        val profileBeforeUpdate = profileToUpdate.copy()

        profileToUpdate.password = passwordUtil.encode(newPassword)
        val updatedProfile = profileRepository.save(profileToUpdate)

        logger.logInfo(
            PROFILE_PASSWORD_UPDATED_IN_DATABASE,
            mapOf(
                PROFILE to profileBeforeUpdate,
                UPDATED_PROFILE to updatedProfile,
            ),
        )

        return updatedProfile
    }

    fun deleteProfile(id: Int): Profile {
        val profileToDelete = getProfile(id)

        if (profileToDelete.isDeleted) {
            throw ProfileNotFoundException(id)
        }

        profileToDelete.isDeleted = true
        val deletedProfile = profileRepository.save(profileToDelete)

        logger.logInfo(
            PROFILE_DELETED_FROM_DATABASE,
            mapOf(PROFILE to deletedProfile),
        )

        return deletedProfile
    }
}

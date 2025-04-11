package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_PROFILE_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.DELETE_PROFILE_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.DELETE_PROFILE_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_RECEIVED
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE
import hu.netsurf.erp.usermanagement.constant.LogEventConstants.USER_INPUT_MAPPED_TO_USER
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.PROFILE
import hu.netsurf.erp.usermanagement.constant.LoggerConstants.PROFILE_INPUT
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import hu.netsurf.erp.usermanagement.input.DeleteProfileInput
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput
import hu.netsurf.erp.usermanagement.model.Profile
import hu.netsurf.erp.usermanagement.service.ProfileService
import hu.netsurf.erp.usermanagement.util.sanitization.ProfileInputSanitizer
import hu.netsurf.erp.usermanagement.util.sanitization.UpdateProfilePasswordInputSanitizer
import hu.netsurf.erp.usermanagement.util.validation.ProfileInputValidator
import hu.netsurf.erp.usermanagement.util.validation.UpdateProfilePasswordInputValidator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProfileController(
    private val profileService: ProfileService,
    private val profileInputSanitizer: ProfileInputSanitizer,
    private val profileInputValidator: ProfileInputValidator,
    private val updateProfilePasswordInputSanitizer: UpdateProfilePasswordInputSanitizer,
    private val updateProfilePasswordInputValidator: UpdateProfilePasswordInputValidator,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @MutationMapping(name = "createProfile")
    fun createProfile(
        @Argument input: CreateProfileInput,
    ): Profile {
        logger.logInfo(CREATE_PROFILE_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedProfileInput = profileInputSanitizer.sanitize(input)
        profileInputValidator.validate(sanitizedProfileInput)

        val profile = sanitizedProfileInput.toProfile()

        logger.logInfo(
            USER_INPUT_MAPPED_TO_USER,
            mapOf(
                PROFILE_INPUT to sanitizedProfileInput,
                PROFILE to profile,
            ),
        )

        val createdProfile = profileService.createProfile(profile)

        logger.logInfo(CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return createdProfile
    }

    @MutationMapping(name = "updateProfilePassword")
    fun updateProfilePassword(
        @Argument input: UpdateProfilePasswordInput,
    ): Profile {
        logger.logInfo(UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_RECEIVED)

        val sanitizedUpdateUserPasswordInput = updateProfilePasswordInputSanitizer.sanitize(input)
        updateProfilePasswordInputValidator.validate(sanitizedUpdateUserPasswordInput)

        val updatedUser =
            profileService.updateProfilePassword(
                input.id,
                Pair(input.currentPassword, input.newPassword),
            )

        logger.logInfo(UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return updatedUser
    }

    @MutationMapping(name = "deleteProfile")
    fun deleteUser(
        @Argument input: DeleteProfileInput,
    ): Profile {
        logger.logInfo(DELETE_PROFILE_GRAPHQL_MUTATION_RECEIVED)

        val deletedProfile = profileService.deleteProfile(input.userId)

        logger.logInfo(DELETE_PROFILE_GRAPHQL_MUTATION_SUCCESS_RESPONSE)

        return deletedProfile
    }
}

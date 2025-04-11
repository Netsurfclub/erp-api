package hu.netsurf.erp.usermanagement.controller

import hu.netsurf.erp.usermanagement.service.ProfileService
import hu.netsurf.erp.usermanagement.testobject.ProfileTestObject.Companion.profile1
import hu.netsurf.erp.usermanagement.util.ProfileInputSanitizer
import hu.netsurf.erp.usermanagement.util.ProfileInputValidator
import hu.netsurf.erp.usermanagement.util.UpdateProfilePasswordInputSanitizer
import hu.netsurf.erp.usermanagement.util.UpdateProfilePasswordInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1 as createProfileInput1
import hu.netsurf.erp.usermanagement.testobject.DeleteProfileInputTestObject.Companion.input1 as deleteProfileInput1
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1 as updateProfilePasswordInput1

class ProfileControllerTests {
    private val profileService: ProfileService = mockk()
    private val profileInputSanitizer: ProfileInputSanitizer = mockk()
    private val profileInputValidator: ProfileInputValidator = mockk()
    private val updateProfilePasswordInputSanitizer: UpdateProfilePasswordInputSanitizer = mockk()
    private val updateProfilePasswordInputValidator: UpdateProfilePasswordInputValidator = mockk()
    private val profileController: ProfileController =
        ProfileController(
            profileService,
            profileInputSanitizer,
            profileInputValidator,
            updateProfilePasswordInputSanitizer,
            updateProfilePasswordInputValidator,
        )

    @Test
    fun `createProfile test happy path`() {
        every {
            profileInputSanitizer.sanitize(any())
        } returns createProfileInput1()
        justRun { profileInputValidator.validate(any()) }
        every {
            profileService.createProfile(any())
        } returns profile1()

        val result = profileController.createProfile(createProfileInput1())
        assertEquals(profile1(), result)
    }

    @Test
    fun `updateProfilePassword test happy path`() {
        every {
            updateProfilePasswordInputSanitizer.sanitize(any())
        } returns updateProfilePasswordInput1()
        every {
            profileService.getProfile(any())
        } returns profile1()
        justRun { updateProfilePasswordInputValidator.validate(any()) }
        every {
            profileService.updateProfilePassword(any(), any())
        } returns profile1()

        val result = profileController.updateProfilePassword(updateProfilePasswordInput1())
        assertEquals(profile1(), result)
    }

    @Test
    fun `deleteProfile test happy path`() {
        every {
            profileService.deleteProfile(any())
        } returns profile1()

        val result = profileController.deleteUser(deleteProfileInput1())
        assertEquals(profile1(), result)
    }
}

package hu.netsurf.erp.usermanagement.service

import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.HASHED_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.NEW_PASSWORD
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.ProfileAlreadyExistException
import hu.netsurf.erp.usermanagement.exception.ProfileNotFoundException
import hu.netsurf.erp.usermanagement.repository.ProfileRepository
import hu.netsurf.erp.usermanagement.testobject.ProfileTestObject.Companion.profile1
import hu.netsurf.erp.usermanagement.testobject.ProfileTestObject.Companion.profile1IsDeleted
import hu.netsurf.erp.usermanagement.util.PasswordUtil
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class ProfileServiceTests {
    private val profileRepository: ProfileRepository = mockk()
    private val passwordUtil: PasswordUtil = mockk()
    private val profileService: ProfileService = ProfileService(profileRepository, passwordUtil)
    private val currentAndNewPassword: Pair<String, String> = Pair(PASSWORD, NEW_PASSWORD)

    @Test
    fun `getProfile test happy path`() {
        every {
            profileRepository.findById(any())
        } returns Optional.of(profile1())

        val result = profileService.getProfile(1)
        assertNotNull(result)
        assertEquals(profile1(), result)
    }

    @Test
    fun `getProfile test unhappy path - profile not found by id`() {
        every {
            profileRepository.findById(any())
        } returns Optional.empty()

        assertThrows<ProfileNotFoundException> {
            profileService.getProfile(3)
        }
    }

    @Test
    fun `createProfile test happy path`() {
        every {
            profileRepository.findByUserId(any())
        } returns Optional.empty()
        every {
            passwordUtil.encode(any())
        } returns HASHED_PASSWORD
        every {
            profileRepository.save(any())
        } returns profile1()

        val result = profileService.createProfile(profile1())
        assertNotNull(result)
        assertEquals(profile1(), result)
    }

    @Test
    fun `createProfile test unhappy path - profile already been created for given user`() {
        every {
            profileRepository.findByUserId(any())
        } returns Optional.of(profile1())
        every {
            profileRepository.save(any())
        } returns profile1()

        assertThrows<ProfileAlreadyExistException> {
            profileService.createProfile(profile1())
        }
    }

    @Test
    fun `updateProfilePassword test happy path`() {
        coEvery {
            profileRepository.findById(any())
        } returns Optional.of(profile1())
        every {
            passwordUtil.verify(any(), any())
        } returns true
        every {
            passwordUtil.encode(any())
        } returns HASHED_PASSWORD
        every {
            profileRepository.save(any())
        } returns profile1()

        val result = profileService.updateProfilePassword(1, currentAndNewPassword)
        assertNotNull(result)
        assertEquals(profile1(), result)
    }

    @Test
    fun `updateProfilePassword test unhappy path - profile is already deleted (not found in database)`() {
        coEvery {
            profileRepository.findById(any())
        } returns Optional.of(profile1IsDeleted())

        assertThrows<ProfileNotFoundException> {
            profileService.updateProfilePassword(1, currentAndNewPassword)
        }
    }

    @Test
    fun `updateProfilePassword test unhappy path - current password and password in database not matches`() {
        coEvery {
            profileRepository.findById(any())
        } returns Optional.of(profile1())
        coEvery { passwordUtil.verify(any(), any()) } returns false

        assertThrows<CurrentPasswordAndPasswordInDatabaseNotMatchesException> {
            profileService.updateProfilePassword(1, currentAndNewPassword)
        }
    }

    @Test
    fun `deleteProfile test happy path`() {
        every {
            profileRepository.findById(any())
        } returns Optional.of(profile1())
        every {
            profileRepository.save(any())
        } returns profile1IsDeleted()

        val result = profileService.deleteProfile(1)
        assertNotNull(result)
        assertEquals(profile1IsDeleted(), result)
    }

    @Test
    fun `deleteProfile test unhappy path - profile is already deleted (not found in database)`() {
        every {
            profileRepository.findById(any())
        } returns Optional.of(profile1IsDeleted())

        assertThrows<ProfileNotFoundException> {
            profileService.deleteProfile(1)
        }
    }
}

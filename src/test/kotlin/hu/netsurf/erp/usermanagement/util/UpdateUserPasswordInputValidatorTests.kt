package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyConfirmNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyCurrentPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithInvalidConfirmNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithInvalidCurrentPassword
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateUserPasswordInputValidatorTests {
    private val passwordUtil: PasswordUtil = mockk()
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator = UpdateUserPasswordInputValidator(passwordUtil)
    private val passwordInDatabase: String = PASSWORD

    companion object {
        @JvmStatic
        fun updateUserPasswordInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("current password is empty", input1WithEmptyCurrentPassword()),
                Arguments.of("new password is empty", input1WithEmptyNewPassword()),
                Arguments.of("confirm new password is empty", input1WithEmptyConfirmNewPassword()),
            )

        @JvmStatic
        fun invalidPasswordParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("no number", "p@sSwOrD"),
                Arguments.of("no lowercase character", "P@SSW0RD"),
                Arguments.of("no uppercase character", "p@ssword"),
                Arguments.of("no special character", "pAsSwOrD"),
                Arguments.of("too short", "p"),
                Arguments.of("too long", "p@sSw0rDp@sSw0rD"),
            )
    }

    @BeforeEach
    fun setup() {
        coEvery { passwordUtil.verify(any(), any()) } returns true
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            updateUserPasswordInputValidator.validate(input1(), passwordInDatabase)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateUserPasswordInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        updateUserPasswordInput: UpdateUserPasswordInput,
    ) {
        assertThrows<EmptyFieldException> {
            updateUserPasswordInputValidator.validate(updateUserPasswordInput, passwordInDatabase)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidPasswordParams")
    fun `validate test unhappy path - invalid new password format`(
        testCase: String,
        newPassword: String,
    ) {
        val input =
            UpdateUserPasswordInput(
                userId = 1,
                currentPassword = PASSWORD,
                newPassword = newPassword,
                confirmNewPassword = newPassword,
            )

        assertThrows<InvalidPasswordFormatException> {
            updateUserPasswordInputValidator.validate(input, passwordInDatabase)
        }
    }

    @Test
    fun `validate test unhappy path - current password and password in database not matches`() {
        coEvery { passwordUtil.verify(any(), any()) } returns false

        assertThrows<CurrentPasswordAndPasswordInDatabaseNotMatchesException> {
            updateUserPasswordInputValidator.validate(input1WithInvalidCurrentPassword(), passwordInDatabase)
        }
    }

    @Test
    fun `validate test unhappy path - new password and confirm new password not matches`() {
        assertThrows<NewPasswordAndConfirmNewPasswordNotMatchesException> {
            updateUserPasswordInputValidator.validate(input1WithInvalidConfirmNewPassword(), passwordInDatabase)
        }
    }
}

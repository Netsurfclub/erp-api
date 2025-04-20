package hu.netsurf.erp.usermanagement.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_PASSWORD
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateProfilePasswordInput
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1WithEmptyConfirmNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1WithEmptyCurrentPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1WithEmptyNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateProfilePasswordInputTestObject.Companion.input1WithInvalidConfirmNewPassword
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateProfilePasswordInputValidatorTests {
    private val updateUserPasswordInputValidator: UpdateProfilePasswordInputValidator = UpdateProfilePasswordInputValidator()

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

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            updateUserPasswordInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateUserPasswordInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        input: UpdateProfilePasswordInput,
    ) {
        assertThrows<EmptyFieldException> {
            updateUserPasswordInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidPasswordParams")
    fun `validate test unhappy path - invalid new password format`(
        testCase: String,
        newPassword: String,
    ) {
        val input =
            UpdateProfilePasswordInput(
                id = 1,
                currentPassword = PROFILE_1_PASSWORD,
                newPassword = newPassword,
                confirmNewPassword = newPassword,
            )

        assertThrows<InvalidPasswordFormatException> {
            updateUserPasswordInputValidator.validate(input)
        }
    }

    @Test
    fun `validate test unhappy path - new password and confirm new password not matches`() {
        assertThrows<NewPasswordAndConfirmNewPasswordNotMatchesException> {
            updateUserPasswordInputValidator.validate(input1WithInvalidConfirmNewPassword())
        }
    }
}

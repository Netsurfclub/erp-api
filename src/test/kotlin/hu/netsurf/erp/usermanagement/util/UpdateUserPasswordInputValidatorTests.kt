package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndPasswordInDatabaseMatchesException
import hu.netsurf.erp.usermanagement.model.UpdateUserPasswordInput
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateUserPasswordInputValidatorTests {
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator = UpdateUserPasswordInputValidator()
    private val passwordInDatabase: String = "pAsSwOrD"

    companion object {
        @JvmStatic
        fun updateUserPasswordInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("current password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyCurrentPassword()),
                Arguments.of("new password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyNewPassword()),
                Arguments.of("confirm new password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyConfirmNewPassword()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            updateUserPasswordInputValidator.validate(UpdateUserPasswordInputTestObject.updateUserPasswordInput1(), passwordInDatabase)
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

    @Test
    fun `validate test unhappy path - current password and password in database not matches`() {
        assertThrows<CurrentPasswordAndPasswordInDatabaseNotMatchesException> {
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidCurrentPassword(),
                passwordInDatabase,
            )
        }
    }

    @Test
    fun `validate test unhappy path - new password and password in database matches`() {
        assertThrows<NewPasswordAndPasswordInDatabaseMatchesException> {
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithNewPasswordAndPasswordInDatabaseMatches(),
                passwordInDatabase,
            )
        }
    }

    @Test
    fun `validate test unhappy path - new password and confirm new password not matches`() {
        assertThrows<NewPasswordAndConfirmNewPasswordNotMatchesException> {
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidConfirmNewPassword(),
                passwordInDatabase,
            )
        }
    }
}

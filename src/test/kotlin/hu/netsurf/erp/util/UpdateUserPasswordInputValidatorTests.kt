package hu.netsurf.erp.util

import hu.netsurf.erp.UserTestConstants.PASSWORD
import hu.netsurf.erp.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.exception.EmptyFieldException
import hu.netsurf.erp.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.exception.NewPasswordAndPasswordInDatabaseMatchesException
import hu.netsurf.erp.input.UpdateUserPasswordInput
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.updateUserPasswordInput1
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.updateUserPasswordInput1WithInvalidConfirmNewPassword
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.updateUserPasswordInput1WithInvalidCurrentPassword
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.updateUserPasswordInput1WithNewPasswordAndPasswordInDatabaseMatches
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.userInput1WithEmptyConfirmNewPassword
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.userInput1WithEmptyCurrentPassword
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject.Companion.userInput1WithEmptyNewPassword
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class UpdateUserPasswordInputValidatorTests {
    private val updateUserPasswordInputValidator: UpdateUserPasswordInputValidator = UpdateUserPasswordInputValidator()
    private val passwordInDatabase: String = PASSWORD

    companion object {
        @JvmStatic
        fun updateUserPasswordInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("current password is empty", userInput1WithEmptyCurrentPassword()),
                Arguments.of("new password is empty", userInput1WithEmptyNewPassword()),
                Arguments.of("confirm new password is empty", userInput1WithEmptyConfirmNewPassword()),
            )
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            updateUserPasswordInputValidator.validate(updateUserPasswordInput1(), passwordInDatabase)
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
                updateUserPasswordInput1WithInvalidCurrentPassword(),
                passwordInDatabase,
            )
        }
    }

    @Test
    fun `validate test unhappy path - new password and password in database matches`() {
        assertThrows<NewPasswordAndPasswordInDatabaseMatchesException> {
            updateUserPasswordInputValidator.validate(
                updateUserPasswordInput1WithNewPasswordAndPasswordInDatabaseMatches(),
                passwordInDatabase,
            )
        }
    }

    @Test
    fun `validate test unhappy path - new password and confirm new password not matches`() {
        assertThrows<NewPasswordAndConfirmNewPasswordNotMatchesException> {
            updateUserPasswordInputValidator.validate(
                updateUserPasswordInput1WithInvalidConfirmNewPassword(),
                passwordInDatabase,
            )
        }
    }
}

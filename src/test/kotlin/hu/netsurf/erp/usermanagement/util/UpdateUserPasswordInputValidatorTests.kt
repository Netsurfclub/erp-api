package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.usermanagement.constant.UserTestConstants.PASSWORD
import hu.netsurf.erp.usermanagement.exception.CurrentPasswordAndPasswordInDatabaseNotMatchesException
import hu.netsurf.erp.usermanagement.exception.NewPasswordAndConfirmNewPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.UpdateUserPasswordInput
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyConfirmNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyCurrentPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithEmptyNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithInvalidConfirmNewPassword
import hu.netsurf.erp.usermanagement.testobject.UpdateUserPasswordInputTestObject.Companion.input1WithInvalidCurrentPassword
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
                Arguments.of("current password is empty", input1WithEmptyCurrentPassword()),
                Arguments.of("new password is empty", input1WithEmptyNewPassword()),
                Arguments.of("confirm new password is empty", input1WithEmptyConfirmNewPassword()),
            )
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

    @Test
    fun `validate test unhappy path - current password and password in database not matches`() {
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

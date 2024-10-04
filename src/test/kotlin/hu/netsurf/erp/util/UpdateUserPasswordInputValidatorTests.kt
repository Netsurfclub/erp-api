package hu.netsurf.erp.util

import hu.netsurf.erp.TestConstants.PASSWORD
import hu.netsurf.erp.model.UpdateUserPasswordInput
import hu.netsurf.erp.testobject.UpdateUserPasswordInputTestObject
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
                Arguments.of("current password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyCurrentPassword()),
                Arguments.of("new password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyNewPassword()),
                Arguments.of("confirm new password is empty", UpdateUserPasswordInputTestObject.userInput1WithEmptyConfirmNewPassword()),
            )
    }

    @Test
    fun `validate test happy path`() {
        val result =
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1(),
                passwordInDatabase,
            )
        assertEquals("Validation success.", result.message)
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("updateUserPasswordInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        updateUserPasswordInput: UpdateUserPasswordInput,
    ) {
        val result = updateUserPasswordInputValidator.validate(updateUserPasswordInput, passwordInDatabase)
        assertEquals("Üres mező.", result.message)
    }

    @Test
    fun `validate test unhappy path - current password and password in database not matches`() {
        val result =
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidCurrentPassword(),
                passwordInDatabase,
            )
        assertEquals("A jelenlegi jelszó hibás.", result.message)
    }

    @Test
    fun `validate test unhappy path - new password and password in database matches`() {
        val result =
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithNewPasswordAndPasswordInDatabaseMatches(),
                passwordInDatabase,
            )
        assertEquals("Nem adható meg új jelszónak a jelenlegi jelszó.", result.message)
    }

    @Test
    fun `validate test unhappy path - new password and confirm new password not matches`() {
        val result =
            updateUserPasswordInputValidator.validate(
                UpdateUserPasswordInputTestObject.updateUserPasswordInput1WithInvalidConfirmNewPassword(),
                passwordInDatabase,
            )
        assertEquals("A jelszó megerősítése sikertelen.", result.message)
    }
}

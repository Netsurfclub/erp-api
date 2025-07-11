package hu.netsurf.erp.usermanagement.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.constant.ProfileTestConstants.PROFILE_1_USERNAME
import hu.netsurf.erp.usermanagement.exception.InvalidPasswordFormatException
import hu.netsurf.erp.usermanagement.exception.PasswordAndConfirmPasswordNotMatchesException
import hu.netsurf.erp.usermanagement.input.CreateProfileInput
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithEmptyConfirmPassword
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithEmptyPassword
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithEmptyUsername
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithInvalidConfirmPassword
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithLongUsername
import hu.netsurf.erp.usermanagement.testobject.CreateProfileInputTestObject.Companion.input1WithShortUsername
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CreateProfileInputValidatorTests {
    private val createProfileInputValidator: CreateProfileInputValidator = CreateProfileInputValidator()

    companion object {
        @JvmStatic
        fun userInputEmptyFieldParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username is empty", input1WithEmptyUsername()),
                Arguments.of("password is empty", input1WithEmptyPassword()),
                Arguments.of("confirmPassword is empty", input1WithEmptyConfirmPassword()),
            )

        @JvmStatic
        fun userInputFieldLengthParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("username is too short", input1WithShortUsername()),
                Arguments.of("username is too long", input1WithLongUsername()),
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
            createProfileInputValidator.validate(input1())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputEmptyFieldParams")
    fun `validate tests unhappy path - empty fields`(
        testCase: String,
        input: CreateProfileInput,
    ) {
        assertThrows<EmptyFieldException> {
            createProfileInputValidator.validate(input)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("userInputFieldLengthParams")
    fun `validate tests unhappy path - length check`(
        testCase: String,
        input: CreateProfileInput,
    ) {
        assertThrows<InvalidLengthException> {
            createProfileInputValidator.validate(input)
        }
    }

    @Test
    fun `validate test unhappy path - password and confirm password not matches`() {
        assertThrows<PasswordAndConfirmPasswordNotMatchesException> {
            createProfileInputValidator.validate(input1WithInvalidConfirmPassword())
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("invalidPasswordParams")
    fun `validate test unhappy path - invalid password format`(
        testCase: String,
        password: String,
    ) {
        val input =
            CreateProfileInput(
                userId = 1,
                username = PROFILE_1_USERNAME,
                password = password,
                confirmPassword = password,
            )

        assertThrows<InvalidPasswordFormatException> {
            createProfileInputValidator.validate(input)
        }
    }
}

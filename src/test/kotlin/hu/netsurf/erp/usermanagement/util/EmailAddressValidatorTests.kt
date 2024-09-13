package hu.netsurf.erp.usermanagement.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class EmailAddressValidatorTests {
    private val emailAddressValidator: EmailAddressValidator = EmailAddressValidator()

    companion object {
        @JvmStatic
        fun emailAddressParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("test@netsurfclub.hu", "test@netsurfclub.hu", true),
                Arguments.of("testnetsurfclub.hu", "testnetsurfclub.hu", false),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("emailAddressParams")
    fun `isValid tests`(
        testCase: String,
        input: String,
        isValid: Boolean,
    ) {
        val result = emailAddressValidator.isValid(input)
        assertEquals(isValid, result)
    }
}

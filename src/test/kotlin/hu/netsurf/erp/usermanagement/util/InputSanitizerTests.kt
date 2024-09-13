package hu.netsurf.erp.usermanagement.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.boot.test.context.SpringBootTest
import java.util.stream.Stream

@SpringBootTest
class InputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()

    companion object {
        @JvmStatic
        fun inputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("input"),
                Arguments.of(" input"),
                Arguments.of("input "),
                Arguments.of(" input "),
            )
    }

    @ParameterizedTest
    @MethodSource("inputParams")
    fun `sanitize tests`(input: String) {
        val result = inputSanitizer.sanitize(input)
        assertEquals("input", result)
    }
}

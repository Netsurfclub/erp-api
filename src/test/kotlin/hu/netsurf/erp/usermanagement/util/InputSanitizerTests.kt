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
                Arguments.of("no whitespace", "input"),
                Arguments.of("one whitespace on left side", " input"),
                Arguments.of("one whitespace on right side", "input "),
                Arguments.of("whitespaces on both right and left side", " input "),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("inputParams")
    fun `sanitize tests`(
        testCase: String,
        input: String,
    ) {
        val result = inputSanitizer.sanitize(input)
        assertEquals("input", result)
    }
}

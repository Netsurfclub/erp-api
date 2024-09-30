package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.TestConstants.INPUT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class InputSanitizerTests {
    private val inputSanitizer: InputSanitizer = InputSanitizer()

    companion object {
        @JvmStatic
        fun inputParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of("no whitespace", INPUT),
                Arguments.of("one whitespace on left side", " $INPUT"),
                Arguments.of("one whitespace on right side", "$INPUT "),
                Arguments.of("whitespaces on both right and left side", " $INPUT "),
            )
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("inputParams")
    fun `sanitize tests`(
        testCase: String,
        input: String,
    ) {
        val result = inputSanitizer.sanitize(input)
        assertEquals(INPUT, result)
    }
}

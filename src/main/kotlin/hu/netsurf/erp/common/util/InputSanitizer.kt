package hu.netsurf.erp.common.util

import org.springframework.stereotype.Component

@Component
class InputSanitizer {
    fun sanitize(input: String): String = trim(input)

    private fun trim(input: String): String = input.trim()
}

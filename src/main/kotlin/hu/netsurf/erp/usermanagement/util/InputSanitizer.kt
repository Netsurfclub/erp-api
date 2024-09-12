package hu.netsurf.erp.usermanagement.util

import org.springframework.stereotype.Component

@Component
class InputSanitizer {
    fun sanitize(input: String): String {
        return trim(input)
    }

    private fun trim(input: String): String = input.trim()
}

package hu.netsurf.erp.usermanagement.util

import org.springframework.stereotype.Component

@Component
class InputSanitizerUtil {
    fun sanitize(input: String): String {
        return trim(input)
    }

    private fun trim(input: String): String = input.trim()
}

package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.usermanagement.constant.ValidationConstants.EMAIL_ADDRESS_REGEX
import org.springframework.stereotype.Component

@Component
class EmailAddressValidator {
    fun isValid(email: String): Boolean = email.matches(Regex(EMAIL_ADDRESS_REGEX))
}

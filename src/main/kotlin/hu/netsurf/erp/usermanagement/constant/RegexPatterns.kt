package hu.netsurf.erp.usermanagement.constant

object RegexPatterns {
    const val PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,15}$"
}

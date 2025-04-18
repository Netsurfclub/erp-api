﻿package hu.netsurf.erp.usermanagement.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.usermanagement.input.CreateUserInput
import org.springframework.stereotype.Component

@Component
class CreateUserInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateUserInput): CreateUserInput =
        CreateUserInput(
            firstName = inputSanitizer.sanitize(input.firstName),
            lastName = inputSanitizer.sanitize(input.lastName),
            email = inputSanitizer.sanitize(input.email),
        )
}

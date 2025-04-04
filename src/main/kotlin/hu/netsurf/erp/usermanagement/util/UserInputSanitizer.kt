﻿package hu.netsurf.erp.usermanagement.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.usermanagement.input.UserInput
import org.springframework.stereotype.Component

@Component
class UserInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(userInput: UserInput): UserInput =
        UserInput(
            username = inputSanitizer.sanitize(userInput.username),
            password = inputSanitizer.sanitize(userInput.password),
            confirmPassword = inputSanitizer.sanitize(userInput.confirmPassword),
            firstName = inputSanitizer.sanitize(userInput.firstName),
            lastName = inputSanitizer.sanitize(userInput.lastName),
            email = inputSanitizer.sanitize(userInput.email),
        )
}

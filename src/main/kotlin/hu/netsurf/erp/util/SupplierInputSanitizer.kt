package hu.netsurf.erp.util

import hu.netsurf.erp.input.SupplierInput
import org.springframework.stereotype.Component

@Component
class SupplierInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(userInput: SupplierInput): SupplierInput =
        SupplierInput(
            name = inputSanitizer.sanitize(userInput.name),
            email = inputSanitizer.sanitize(userInput.email),
            phone = inputSanitizer.sanitize(userInput.phone),
        )
}

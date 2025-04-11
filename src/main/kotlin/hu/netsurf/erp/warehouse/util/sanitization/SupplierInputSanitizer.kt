package hu.netsurf.erp.warehouse.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import org.springframework.stereotype.Component

@Component
class SupplierInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateSupplierInput): CreateSupplierInput {
        var phone: String? = null
        var email: String? = null

        if (input.phone != null) {
            phone = inputSanitizer.sanitize(input.phone)
        }

        if (input.email != null) {
            email = inputSanitizer.sanitize(input.email)
        }

        return CreateSupplierInput(
            name = inputSanitizer.sanitize(input.name),
            phone = phone,
            email = email,
        )
    }
}

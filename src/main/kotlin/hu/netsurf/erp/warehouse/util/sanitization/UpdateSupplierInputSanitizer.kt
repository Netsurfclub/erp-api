package hu.netsurf.erp.warehouse.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput
import org.springframework.stereotype.Component

@Component
class UpdateSupplierInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: UpdateSupplierInput): UpdateSupplierInput {
        var name: String? = null
        var phone: String? = null
        var email: String? = null

        if (input.name != null) {
            name = inputSanitizer.sanitize(input.name)
        }

        if (input.phone != null) {
            phone = inputSanitizer.sanitize(input.phone)
        }

        if (input.email != null) {
            email = inputSanitizer.sanitize(input.email)
        }

        return UpdateSupplierInput(
            name = name,
            phone = phone,
            email = email,
        )
    }
}

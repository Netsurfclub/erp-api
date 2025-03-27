package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.warehouse.input.SupplierInput
import org.springframework.stereotype.Component

@Component
class SupplierInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(supplierInput: SupplierInput): SupplierInput {
        var phone: String? = null
        var email: String? = null

        if (supplierInput.phone != null) {
            phone = inputSanitizer.sanitize(supplierInput.phone)
        }

        if (supplierInput.email != null) {
            email = inputSanitizer.sanitize(supplierInput.email)
        }

        return SupplierInput(
            name = inputSanitizer.sanitize(supplierInput.name),
            phone = phone,
            email = email,
        )
    }
}

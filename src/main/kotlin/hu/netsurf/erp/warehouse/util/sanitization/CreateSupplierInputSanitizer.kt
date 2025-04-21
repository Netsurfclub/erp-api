package hu.netsurf.erp.warehouse.util.sanitization

import hu.netsurf.erp.common.util.sanitization.InputSanitizer
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import org.springframework.stereotype.Component

@Component
class CreateSupplierInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateSupplierInput): CreateSupplierInput =
        CreateSupplierInput(
            name = inputSanitizer.sanitize(input.name),
            phone = input.phone?.let { inputSanitizer.sanitize(it) },
            email = input.email?.let { inputSanitizer.sanitize(it) },
        )
}

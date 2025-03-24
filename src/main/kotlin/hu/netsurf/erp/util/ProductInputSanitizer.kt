package hu.netsurf.erp.util

import hu.netsurf.erp.input.ProductInput
import org.springframework.stereotype.Component

@Component
class ProductInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(productInput: ProductInput): ProductInput =
        ProductInput(
            name = inputSanitizer.sanitize(productInput.name),
            supplierId = productInput.supplierId,
            price = productInput.price,
            unit = inputSanitizer.sanitize(productInput.unit),
            onStock = productInput.onStock,
        )
}

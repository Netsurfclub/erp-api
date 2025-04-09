package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.util.InputSanitizer
import hu.netsurf.erp.warehouse.input.CreateProductInput
import org.springframework.stereotype.Component

@Component
class ProductInputSanitizer(
    private val inputSanitizer: InputSanitizer,
) {
    fun sanitize(input: CreateProductInput): CreateProductInput =
        CreateProductInput(
            name = inputSanitizer.sanitize(input.name),
            supplierId = input.supplierId,
            price = input.price,
            unit = inputSanitizer.sanitize(input.unit),
            onStock = input.onStock,
        )
}

package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.ProductInput
import org.springframework.stereotype.Component

@Component
class ProductInputValidator {
    fun validate(productInput: ProductInput) {
        if (
            productInput.nameIsEmpty() ||
            productInput.unitIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (
            productInput.nameIsShort() ||
            productInput.nameIsLong() ||
            productInput.unitIsShort() ||
            productInput.unitIsLong()
        ) {
            throw InvalidLengthException()
        }
    }
}

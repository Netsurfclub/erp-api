package hu.netsurf.erp.util

import hu.netsurf.erp.exception.EmptyFieldException
import hu.netsurf.erp.exception.InvalidLengthException
import hu.netsurf.erp.input.ProductInput
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

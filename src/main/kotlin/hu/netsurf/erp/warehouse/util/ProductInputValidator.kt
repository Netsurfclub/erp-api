package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.CreateProductInput
import org.springframework.stereotype.Component

@Component
class ProductInputValidator {
    fun validate(input: CreateProductInput) {
        if (
            input.nameIsEmpty() ||
            input.unitIsEmpty()
        ) {
            throw EmptyFieldException()
        }

        if (
            input.nameIsShort() ||
            input.nameIsLong() ||
            input.unitIsShort() ||
            input.unitIsLong()
        ) {
            throw InvalidLengthException()
        }
    }
}

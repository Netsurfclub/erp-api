﻿package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.CreateProductInput
import org.springframework.stereotype.Component

@Component
class CreateProductInputValidator {
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

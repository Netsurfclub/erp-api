package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.warehouse.input.CreateSupplierInput
import org.springframework.stereotype.Component

@Component
class SupplierInputValidator {
    fun validate(input: CreateSupplierInput) {
        if (input.nameIsEmpty()) {
            throw EmptyFieldException()
        }

        if (
            input.nameIsShort() ||
            input.nameIsLong() ||
            input.phoneIsShort() ||
            input.phoneIsLong() ||
            input.emailIsShort() ||
            input.emailIsLong()
        ) {
            throw InvalidLengthException()
        }

        if (!input.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }
    }
}

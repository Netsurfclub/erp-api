package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.exception.EmptyFieldException
import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.warehouse.input.SupplierInput
import org.springframework.stereotype.Component

@Component
class SupplierInputValidator {
    fun validate(supplierInput: SupplierInput) {
        if (supplierInput.nameIsEmpty()) {
            throw EmptyFieldException()
        }

        if (
            supplierInput.nameIsShort() ||
            supplierInput.nameIsLong() ||
            supplierInput.emailIsShort() ||
            supplierInput.emailIsLong() ||
            supplierInput.phoneIsShort() ||
            supplierInput.phoneIsLong()
        ) {
            throw InvalidLengthException()
        }

        if (!supplierInput.emailAddressIsValid()) {
            throw InvalidEmailAddressFormatException()
        }
    }
}

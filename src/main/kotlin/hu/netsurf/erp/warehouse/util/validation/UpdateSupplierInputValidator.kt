package hu.netsurf.erp.warehouse.util.validation

import hu.netsurf.erp.common.exception.InvalidLengthException
import hu.netsurf.erp.usermanagement.exception.InvalidEmailAddressFormatException
import hu.netsurf.erp.warehouse.input.UpdateSupplierInput
import org.springframework.stereotype.Component

@Component
class UpdateSupplierInputValidator {
    fun validate(input: UpdateSupplierInput) {
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

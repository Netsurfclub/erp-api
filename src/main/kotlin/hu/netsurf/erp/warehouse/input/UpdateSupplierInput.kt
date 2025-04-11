package hu.netsurf.erp.warehouse.input

import hu.netsurf.erp.common.constant.RegexPatterns.EMAIL_ADDRESS_REGEX
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.EMAIL_MAX_LENGTH
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.EMAIL_MIN_LENGTH
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.NAME_MAX_LENGTH
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.NAME_MIN_LENGTH
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.PHONE_MAX_LENGTH
import hu.netsurf.erp.warehouse.constant.SupplierValidationConstants.PHONE_MIN_LENGTH
import hu.netsurf.erp.warehouse.model.Supplier

data class UpdateSupplierInput(
    val name: String?,
    val phone: String?,
    val email: String?,
) {
    fun nameIsShort(): Boolean = name!!.length <= NAME_MIN_LENGTH

    fun nameIsLong(): Boolean = name!!.length > NAME_MAX_LENGTH

    fun phoneIsShort(): Boolean = phone!!.length <= PHONE_MIN_LENGTH

    fun phoneIsLong(): Boolean = phone!!.length > PHONE_MAX_LENGTH

    fun emailAddressIsValid(): Boolean = email!!.matches(Regex(EMAIL_ADDRESS_REGEX))

    fun emailIsShort(): Boolean = email!!.length <= EMAIL_MIN_LENGTH

    fun emailIsLong(): Boolean = email!!.length > EMAIL_MAX_LENGTH

    fun toSupplier() =
        Supplier(
            name = this.name!!,
            email = this.email!!,
            phone = this.phone!!,
        )
}

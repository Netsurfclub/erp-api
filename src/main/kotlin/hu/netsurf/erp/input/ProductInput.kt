package hu.netsurf.erp.input

import hu.netsurf.erp.constant.ProductValidationConstants.NAME_MAX_LENGTH
import hu.netsurf.erp.constant.ProductValidationConstants.NAME_MIN_LENGTH
import hu.netsurf.erp.constant.ProductValidationConstants.UNIT_MAX_LENGTH
import hu.netsurf.erp.constant.ProductValidationConstants.UNIT_MIN_LENGTH

data class ProductInput(
    val name: String,
    val supplierId: Int,
    val price: Double,
    val unit: String,
    val onStock: Int,
) {
    fun nameIsEmpty(): Boolean = name.isEmpty()

    fun nameIsShort(): Boolean = name.length <= NAME_MIN_LENGTH

    fun nameIsLong(): Boolean = name.length > NAME_MAX_LENGTH

    fun unitIsEmpty(): Boolean = unit.isEmpty()

    fun unitIsShort(): Boolean = unit.length <= UNIT_MIN_LENGTH

    fun unitIsLong(): Boolean = unit.length > UNIT_MAX_LENGTH
}

package hu.netsurf.erp.warehouse.model

data class ProductInput(
    val name: String,
    val supplierId: Int,
    val price: Double,
    val unit: String,
    val onStock: Int,
)

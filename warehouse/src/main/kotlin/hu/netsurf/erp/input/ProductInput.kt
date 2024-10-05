package hu.netsurf.erp.input

data class ProductInput(
    val name: String,
    val supplierId: Int,
    val price: Double,
    val unit: String,
    val onStock: Int,
)

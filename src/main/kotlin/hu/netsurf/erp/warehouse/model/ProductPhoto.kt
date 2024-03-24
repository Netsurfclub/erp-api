package hu.netsurf.erp.warehouse.model

data class ProductPhoto(
    val id: Int,
    val name: String,
    val originalName: String?,
    val size: Long,
    val contentType: String?,
    val productId: Int,
    val product: Product,
)

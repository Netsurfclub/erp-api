package hu.netsurf.erp.warehouse.model

data class ProductPhoto(
    val name: String,
    val originalName: String?,
    val size: Long,
    val contentType: String?,
)

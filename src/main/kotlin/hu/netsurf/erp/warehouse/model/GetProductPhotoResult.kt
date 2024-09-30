package hu.netsurf.erp.warehouse.model

data class GetProductPhotoResult(
    val productPhoto: ByteArray? = null,
    val errorMessage: String? = null,
)

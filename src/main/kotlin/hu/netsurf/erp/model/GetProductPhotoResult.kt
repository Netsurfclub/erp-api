package hu.netsurf.erp.model

data class GetProductPhotoResult(
    val productPhoto: ByteArray? = null,
    val errorMessage: String? = null,
)

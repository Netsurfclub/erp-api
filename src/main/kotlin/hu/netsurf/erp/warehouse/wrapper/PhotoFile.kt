package hu.netsurf.erp.warehouse.wrapper

import hu.netsurf.erp.warehouse.extension.getExtension
import java.io.InputStream

class PhotoFile(
    val isEmpty: Boolean,
    val originalFilename: String?,
    val size: Long,
    val contentType: String?,
    val inputStreamBytes: ByteArray,
) {
    fun asString(): String = "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"

    fun getExtension(): String = this.originalFilename!!.getExtension()

    fun inputStream(): InputStream = this.inputStreamBytes.inputStream()
}

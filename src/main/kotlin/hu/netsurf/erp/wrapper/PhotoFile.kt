package hu.netsurf.erp.wrapper

import hu.netsurf.erp.extension.getExtension
import java.io.InputStream

class PhotoFile(
    val isEmpty: Boolean,
    val originalFilename: String?,
    val size: Long,
    val contentType: String?,
    val inputStream: InputStream,
) {
    fun asString(): String {
        return "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"
    }

    fun getExtension(): String = this.originalFilename!!.getExtension()
}

package hu.netsurf.erp.warehouse.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String {
    val fileExtension = this.originalFilename!!.split('.')[1]
    return ".$fileExtension".lowercase()
}

fun MultipartFile.asString(): String {
    return "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"
}

package hu.netsurf.erp.warehouse.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String {
    return this.originalFilename!!.split('.')[1].lowercase()
}

fun MultipartFile.asString(): String {
    return "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"
}

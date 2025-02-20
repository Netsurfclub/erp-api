package hu.netsurf.erp.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String = this.originalFilename!!.getExtension()

fun String.getExtension(): String = this.split('.')[1].lowercase()

fun MultipartFile.asString(): String = "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"

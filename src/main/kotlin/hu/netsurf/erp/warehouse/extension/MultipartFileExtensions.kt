package hu.netsurf.erp.warehouse.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String = this.originalFilename!!.split('.')[1].lowercase()

fun String.getExtension(): String = this.split('.')[1].lowercase()

fun MultipartFile.asString(): String = "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"

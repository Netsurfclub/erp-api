package hu.netsurf.erp.extension

import org.springframework.web.multipart.MultipartFile

// TODO: Encapsulate 'MultipartFile' type to a custom type.

fun MultipartFile.getExtension(): String = this.originalFilename!!.split('.')[1].lowercase()

fun String.getExtension(): String = this.split('.')[1].lowercase()

fun MultipartFile.asString(): String = "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"

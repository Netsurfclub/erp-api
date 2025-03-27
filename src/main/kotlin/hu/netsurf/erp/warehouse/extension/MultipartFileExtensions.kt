package hu.netsurf.erp.warehouse.extension

import hu.netsurf.erp.warehouse.wrapper.PhotoFile
import org.springframework.web.multipart.MultipartFile

fun MultipartFile.asString(): String = "{originalFileName=${this.originalFilename} size=${this.size} contentType=${this.contentType}}"

fun MultipartFile.toPhotoFile(): PhotoFile =
    PhotoFile(
        isEmpty = this.isEmpty,
        originalFilename = this.originalFilename,
        size = this.size,
        contentType = this.contentType,
        inputStreamBytes = this.inputStream.readBytes(),
    )

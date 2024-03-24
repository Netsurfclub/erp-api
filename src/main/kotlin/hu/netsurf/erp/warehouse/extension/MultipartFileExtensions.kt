package hu.netsurf.erp.warehouse.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtensions(): String {
    return this.originalFilename!!.split('.')[1]
}

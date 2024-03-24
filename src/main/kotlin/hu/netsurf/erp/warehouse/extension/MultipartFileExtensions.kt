package hu.netsurf.erp.warehouse.extension

import org.springframework.web.multipart.MultipartFile

fun MultipartFile.getExtension(): String {
    val fileExtension = this.originalFilename!!.split('.')[1]
    return ".$fileExtension".lowercase()
}

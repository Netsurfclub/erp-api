package hu.netsurf.erp.warehouse.util

import org.springframework.web.multipart.MultipartFile

interface FileUtils {
    fun readAllBytes(
        customSubdirectoryName: String,
        fileName: String,
    ): ByteArray

    fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String

    fun storePhoto(
        file: MultipartFile,
        directoryStructurePath: String,
    ): String
}

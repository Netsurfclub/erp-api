package hu.netsurf.erp.warehouse.util

import org.springframework.web.multipart.MultipartFile

interface FileUtils {
    fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String
    fun storePhoto(file: MultipartFile, directoriesPath: String): String
}

package hu.netsurf.erp.util

import hu.netsurf.erp.wrapper.PhotoFile

interface FileUtils {
    fun readAllBytes(
        customSubdirectoryName: String,
        fileName: String,
    ): ByteArray

    fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String

    fun storePhoto(
        file: PhotoFile,
        directoryStructurePath: String,
    ): String
}

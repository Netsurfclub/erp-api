package hu.netsurf.erp.warehouse.util.file

import hu.netsurf.erp.warehouse.wrapper.PhotoFile

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

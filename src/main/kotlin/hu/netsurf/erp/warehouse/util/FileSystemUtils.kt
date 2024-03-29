package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.constant.FileConstants.PHOTOS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.constant.FileConstants.UPLOADS_DIRECTORY_NAME
import hu.netsurf.erp.warehouse.extension.getExtension
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Component
class FileSystemUtils : FileUtils {

    override fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String {
        val uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory = Paths.get(
            UPLOADS_DIRECTORY_NAME,
            PHOTOS_SUBDIRECTORY_NAME,
            customSubdirectoryName,
        )

        if (!Files.exists(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)) {
            Files.createDirectories(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)
        }

        return uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory.toString()
    }

    override fun storePhoto(file: MultipartFile, directoriesPath: String): String {
        val fileName = "${UUID.randomUUID()}${file.getExtension()}"
        val pathWithFileName = Paths.get(directoriesPath, fileName)

        Files.copy(file.inputStream, pathWithFileName)

        return fileName
    }
}

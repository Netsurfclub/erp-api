package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.constants.FileConstants.PHOTOS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.constants.FileConstants.UPLOADS_DIRECTORY_NAME
import hu.netsurf.erp.warehouse.extension.getExtensions
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import kotlin.io.path.readBytes

@Component
class FileSystemUtils : FileUtils {

    override fun createUploadsDirectoryStructureIfNotExists(customSubdirectoryName: String): String {
        val uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory = Paths.get(
            UPLOADS_DIRECTORY_NAME,
            PHOTOS_SUBDIRECTORY_NAME,
            customSubdirectoryName
        )

        if (!Files.exists(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)) {
            Files.createDirectories(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)
        }

        return uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory.toString()
    }

    override fun storePhoto(file: MultipartFile, directoriesPath: String): String {
        val fileName = "${UUID.randomUUID()}.${file.getExtensions()}"
        val pathWithFileName = Paths.get(directoriesPath, fileName)

        val stream = FileOutputStream(pathWithFileName.toFile())
        Files.copy(pathWithFileName, stream) // TODO: Image is corrupted.
        stream.close()

        return fileName
    }
}

package hu.netsurf.erp.warehouse.util.file

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.FileConstants.PHOTOS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.constant.FileConstants.UPLOADS_DIRECTORY_NAME
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PHOTO_BYTES_READ_FROM_FILE_SYSTEM
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PHOTO_STORED_ON_FILE_SYSTEM
import hu.netsurf.erp.warehouse.constant.LogEventConstants.PHOTO_UPLOADS_DIRECTORY_CREATED_ON_FILE_SYSTEM
import hu.netsurf.erp.warehouse.constant.LoggerConstants.FILE_NAME
import hu.netsurf.erp.warehouse.constant.LoggerConstants.UPLOADS_DIRECTORY_PATH
import hu.netsurf.erp.warehouse.wrapper.PhotoFile
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Component
class FileSystemUtils : FileUtils {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override fun readAllBytes(
        customSubdirectoryName: String,
        fileName: String,
    ): ByteArray {
        val uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory =
            Paths.get(
                UPLOADS_DIRECTORY_NAME,
                PHOTOS_SUBDIRECTORY_NAME,
                customSubdirectoryName,
                fileName,
            )

        val bytes = Files.readAllBytes(uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory)

        logger.logInfo(
            PHOTO_BYTES_READ_FROM_FILE_SYSTEM,
            mapOf(
                UPLOADS_DIRECTORY_PATH to uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory,
                FILE_NAME to fileName,
            ),
        )

        return bytes
    }

    override fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String {
        val uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory =
            Paths.get(
                UPLOADS_DIRECTORY_NAME,
                PHOTOS_SUBDIRECTORY_NAME,
                customSubdirectoryName,
            )

        if (!Files.exists(uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory)) {
            Files.createDirectories(uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory)

            logger.logInfo(
                PHOTO_UPLOADS_DIRECTORY_CREATED_ON_FILE_SYSTEM,
                mapOf(UPLOADS_DIRECTORY_PATH to uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory),
            )
        }

        return uploadsDirectoryWithPhotosSubdirectoryAndCustomSubdirectory.toString()
    }

    override fun storePhoto(
        file: PhotoFile,
        directoryStructurePath: String,
    ): String {
        val fileName = "${UUID.randomUUID()}.${file.getExtension()}"
        val pathWithFileName = Paths.get(directoryStructurePath, fileName)

        Files.copy(file.inputStream(), pathWithFileName)

        logger.logInfo(
            PHOTO_STORED_ON_FILE_SYSTEM,
            mapOf(
                UPLOADS_DIRECTORY_PATH to directoryStructurePath,
                FILE_NAME to fileName,
            ),
        )

        return fileName
    }
}

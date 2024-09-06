package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.PHOTO_BYTES_READ_FROM_FILE_SYSTEM
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.PHOTO_STORED_ON_FILE_SYSTEM
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.PHOTO_UPLOADS_DIRECTORY_CREATED_ON_FILE_SYSTEM
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.FILE_NAME
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.UPLOADS_DIRECTORY_PATH
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.constant.FileConstants.PHOTOS_SUBDIRECTORY_NAME
import hu.netsurf.erp.warehouse.constant.FileConstants.UPLOADS_DIRECTORY_NAME
import hu.netsurf.erp.warehouse.extension.getExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID

@Component
class FileSystemUtils : FileUtils {
    val logger: Logger = LoggerFactory.getLogger(FileSystemUtils::class.java)

    override fun readAllBytes(
        customSubdirectoryName: String,
        fileName: String,
    ): ByteArray {
        val uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory =
            Paths.get(
                UPLOADS_DIRECTORY_NAME,
                PHOTOS_SUBDIRECTORY_NAME,
                customSubdirectoryName,
                fileName,
            )

        val bytes = Files.readAllBytes(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)

        logger.logInfo(
            PHOTO_BYTES_READ_FROM_FILE_SYSTEM,
            mapOf(
                UPLOADS_DIRECTORY_PATH to uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory,
                FILE_NAME to fileName,
            ),
        )

        return bytes
    }

    override fun createPhotoUploadsDirectoryStructure(customSubdirectoryName: String): String {
        val uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory =
            Paths.get(
                UPLOADS_DIRECTORY_NAME,
                PHOTOS_SUBDIRECTORY_NAME,
                customSubdirectoryName,
            )

        if (!Files.exists(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)) {
            Files.createDirectories(uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory)

            logger.logInfo(
                PHOTO_UPLOADS_DIRECTORY_CREATED_ON_FILE_SYSTEM,
                mapOf(UPLOADS_DIRECTORY_PATH to uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory),
            )
        }

        return uploadsDirectoryWithPhotosSubDirectoryAndCustomSubdirectory.toString()
    }

    override fun storePhoto(
        file: MultipartFile,
        directoryStructurePath: String,
    ): String {
        val fileName = "${UUID.randomUUID()}.${file.getExtension()}"
        val pathWithFileName = Paths.get(directoryStructurePath, fileName)

        Files.copy(file.inputStream, pathWithFileName)

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

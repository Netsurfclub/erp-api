package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import hu.netsurf.erp.warehouse.exception.EmptyFileException
import hu.netsurf.erp.warehouse.exception.InvalidFileExtensionException
import hu.netsurf.erp.warehouse.wrapper.PhotoFile
import org.springframework.stereotype.Component

@Component
class FileValidator(
    val fileExtensionsConfig: FileExtensionsConfig,
) {
    fun validate(file: PhotoFile) {
        if (file.isEmpty) {
            throw EmptyFileException(fileName = file.originalFilename)
        }

        val fileExtension = file.getExtension()

        if (fileExtensionsConfig.allowedExtensions.all { it != fileExtension }) {
            throw InvalidFileExtensionException(extension = fileExtension)
        }
    }
}

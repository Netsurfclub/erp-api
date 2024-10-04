package hu.netsurf.erp.util

import hu.netsurf.erp.config.FileExtensionsConfig
import hu.netsurf.erp.exception.EmptyFileException
import hu.netsurf.erp.exception.InvalidFileExtensionException
import hu.netsurf.erp.extension.getExtension
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidator(
    val fileExtensionsConfig: FileExtensionsConfig,
) {
    fun validate(file: MultipartFile) {
        if (file.isEmpty) {
            throw EmptyFileException(fileName = file.originalFilename)
        }

        val fileExtension = file.getExtension()

        if (fileExtensionsConfig.allowedExtensions.all { it != fileExtension }) {
            throw InvalidFileExtensionException(extension = fileExtension)
        }
    }
}

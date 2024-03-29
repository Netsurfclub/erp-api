package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import hu.netsurf.erp.warehouse.exception.EmptyFileException
import hu.netsurf.erp.warehouse.exception.InvalidFileExtensionException
import hu.netsurf.erp.warehouse.extension.getExtension
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidator(val fileExtensionsConfig: FileExtensionsConfig) {

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

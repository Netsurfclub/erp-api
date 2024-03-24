package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import hu.netsurf.erp.warehouse.exception.EmptyFileException
import hu.netsurf.erp.warehouse.exception.InvalidFileTypeException
import hu.netsurf.erp.warehouse.extension.getExtension
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidator(val fileExtensionsConfig: FileExtensionsConfig) {
    val logger: Logger = LoggerFactory.getLogger(FileValidator::class.java)

    fun validate(file: MultipartFile) {
        if (file.isEmpty) {
            throw EmptyFileException()
        }

        if (fileExtensionsConfig.allowedExtensions.all { it != file.getExtension() }) {
            throw InvalidFileTypeException()
        }
    }
}

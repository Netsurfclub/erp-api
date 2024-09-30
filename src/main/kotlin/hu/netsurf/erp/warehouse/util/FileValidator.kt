package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import hu.netsurf.erp.warehouse.extension.getExtension
import hu.netsurf.erp.warehouse.model.MultipartFileValidationResult
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidator(
    val fileExtensionsConfig: FileExtensionsConfig,
) {
    fun validate(file: MultipartFile): MultipartFileValidationResult {
        if (file.isEmpty) {
            return MultipartFileValidationResult.emptyFile(file.originalFilename)
        }

        val fileExtension = file.getExtension()

        if (fileExtensionsConfig.allowedExtensions.all { it != fileExtension }) {
            return MultipartFileValidationResult.invalidFileExtension(fileExtension)
        }

        return MultipartFileValidationResult.success()
    }
}

package hu.netsurf.erp.util

import hu.netsurf.erp.PhotoTestConstants.ALLOWED_EXTENSIONS
import hu.netsurf.erp.PhotoTestConstants.INVALID_ORIGINAL_FILE_EXTENSION
import hu.netsurf.erp.PhotoTestConstants.INVALID_ORIGINAL_FILE_NAME
import hu.netsurf.erp.PhotoTestConstants.ORIGINAL_FILE_EXTENSION
import hu.netsurf.erp.PhotoTestConstants.ORIGINAL_FILE_NAME
import hu.netsurf.erp.config.FileExtensionsConfig
import hu.netsurf.erp.exception.EmptyFileException
import hu.netsurf.erp.exception.InvalidFileExtensionException
import hu.netsurf.erp.wrapper.PhotoFile
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FileValidatorTests {
    private val fileExtensionsConfig: FileExtensionsConfig = mockk()
    private val fileValidator: FileValidator = FileValidator(fileExtensionsConfig)
    private val photoFile: PhotoFile = mockk()

    @BeforeEach
    fun setup() {
        every { photoFile.isEmpty } returns false
        every { photoFile.originalFilename } returns ORIGINAL_FILE_NAME
        every { photoFile.getExtension() } returns ORIGINAL_FILE_EXTENSION

        every { fileExtensionsConfig.allowedExtensions } returns ALLOWED_EXTENSIONS
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            fileValidator.validate(photoFile)
        }
    }

    @Test
    fun `validate test unhappy path - file is empty`() {
        every { photoFile.isEmpty } returns true

        assertThrows<EmptyFileException> {
            fileValidator.validate(photoFile)
        }
    }

    @Test
    fun `validate test unhappy path - file has not allowed extension`() {
        every { photoFile.originalFilename } returns INVALID_ORIGINAL_FILE_NAME
        every { photoFile.getExtension() } returns INVALID_ORIGINAL_FILE_EXTENSION

        assertThrows<InvalidFileExtensionException> {
            fileValidator.validate(photoFile)
        }
    }
}

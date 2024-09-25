package hu.netsurf.erp.warehouse.util

import hu.netsurf.erp.TestConstants.ORIGINAL_FILE_NAME
import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import hu.netsurf.erp.warehouse.exception.EmptyFileException
import hu.netsurf.erp.warehouse.exception.InvalidFileExtensionException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.web.multipart.MultipartFile

class FileValidatorTests {
    private val fileExtensionsConfig: FileExtensionsConfig = mockk()
    private val fileValidator: FileValidator = FileValidator(fileExtensionsConfig)
    private val multipartFile: MultipartFile = mockk<MultipartFile>()

    @BeforeEach
    fun setup() {
        every { multipartFile.isEmpty } returns false
        every { multipartFile.originalFilename } returns ORIGINAL_FILE_NAME

        every { fileExtensionsConfig.allowedExtensions } returns listOf("jpg", "jpeg", "png", "bmp")
    }

    @Test
    fun `validate test happy path`() {
        assertDoesNotThrow {
            fileValidator.validate(multipartFile)
        }
    }

    @Test
    fun `validate test unhappy path - file is empty`() {
        every { multipartFile.isEmpty } returns true

        assertThrows<EmptyFileException> {
            fileValidator.validate(multipartFile)
        }
    }

    @Test
    fun `validate test unhappy path - file has not allowed extension`() {
        every { multipartFile.originalFilename } returns "file_name.txt"

        assertThrows<InvalidFileExtensionException> {
            fileValidator.validate(multipartFile)
        }
    }
}

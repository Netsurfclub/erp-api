package hu.netsurf.erp.util

import hu.netsurf.erp.TestConstants.ALLOWED_EXTENSIONS
import hu.netsurf.erp.TestConstants.INVALID_ORIGINAL_FILE_NAME
import hu.netsurf.erp.TestConstants.ORIGINAL_FILE_NAME
import hu.netsurf.erp.config.FileExtensionsConfig
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.web.multipart.MultipartFile

class FileValidatorTests {
    private val fileExtensionsConfig: FileExtensionsConfig = mockk()
    private val fileValidator: FileValidator = FileValidator(fileExtensionsConfig)
    private val multipartFile: MultipartFile = mockk<MultipartFile>()

    @BeforeEach
    fun setup() {
        every { multipartFile.isEmpty } returns false
        every { multipartFile.originalFilename } returns ORIGINAL_FILE_NAME

        every { fileExtensionsConfig.allowedExtensions } returns ALLOWED_EXTENSIONS
    }

    @Test
    fun `validate test happy path`() {
        val result = fileValidator.validate(multipartFile)
        assertEquals("Validation success.", result.message)
    }

    @Test
    fun `validate test unhappy path - file is empty`() {
        every { multipartFile.isEmpty } returns true

        val result = fileValidator.validate(multipartFile)
        assertEquals("Üres fájl: file_name.jpeg", result.message)
    }

    @Test
    fun `validate test unhappy path - file has not allowed extension`() {
        every { multipartFile.originalFilename } returns INVALID_ORIGINAL_FILE_NAME

        val result = fileValidator.validate(multipartFile)
        assertEquals("Hibás fájlformátum: .txt", result.message)
    }
}

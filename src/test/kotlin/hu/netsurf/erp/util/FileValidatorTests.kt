package hu.netsurf.erp.util

import hu.netsurf.erp.PhotoTestConstants.ALLOWED_EXTENSIONS
import hu.netsurf.erp.PhotoTestConstants.BMP
import hu.netsurf.erp.PhotoTestConstants.FILE_NAME_BMP
import hu.netsurf.erp.PhotoTestConstants.FILE_NAME_JPEG
import hu.netsurf.erp.PhotoTestConstants.FILE_NAME_JPG
import hu.netsurf.erp.PhotoTestConstants.FILE_NAME_PNG
import hu.netsurf.erp.PhotoTestConstants.INVALID_FILE_NAME
import hu.netsurf.erp.PhotoTestConstants.JPEG
import hu.netsurf.erp.PhotoTestConstants.JPG
import hu.netsurf.erp.PhotoTestConstants.PNG
import hu.netsurf.erp.PhotoTestConstants.TXT
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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class FileValidatorTests {
    private val fileExtensionsConfig: FileExtensionsConfig = mockk()
    private val fileValidator: FileValidator = FileValidator(fileExtensionsConfig)
    private val photoFile: PhotoFile = mockk()

    companion object {
        @JvmStatic
        fun fileNameAndExtensionParams(): Stream<Arguments> =
            Stream.of(
                Arguments.of(JPG, FILE_NAME_JPG, JPG),
                Arguments.of(JPEG, FILE_NAME_JPEG, JPEG),
                Arguments.of(PNG, FILE_NAME_PNG, PNG),
                Arguments.of(BMP, FILE_NAME_BMP, BMP),
            )
    }

    @BeforeEach
    fun setup() {
        every { photoFile.isEmpty } returns false

        every { fileExtensionsConfig.allowedExtensions } returns ALLOWED_EXTENSIONS
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("fileNameAndExtensionParams")
    fun `validate test happy path`(
        testCase: String,
        fileName: String,
        extension: String,
    ) {
        every { photoFile.originalFilename } returns fileName
        every { photoFile.getExtension() } returns extension

        assertDoesNotThrow {
            fileValidator.validate(photoFile)
        }
    }

    @ParameterizedTest(name = "{index} => {0}")
    @MethodSource("fileNameAndExtensionParams")
    fun `validate test unhappy path - file is empty`(
        testCase: String,
        fileName: String,
        extension: String,
    ) {
        every { photoFile.isEmpty } returns true
        every { photoFile.originalFilename } returns fileName

        assertThrows<EmptyFileException> {
            fileValidator.validate(photoFile)
        }
    }

    @Test
    fun `validate test unhappy path - file has not allowed extension`() {
        every { photoFile.originalFilename } returns INVALID_FILE_NAME
        every { photoFile.getExtension() } returns TXT

        assertThrows<InvalidFileExtensionException> {
            fileValidator.validate(photoFile)
        }
    }
}

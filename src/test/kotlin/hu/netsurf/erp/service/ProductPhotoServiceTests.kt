package hu.netsurf.erp.service

import hu.netsurf.erp.PhotoTestConstants.FILE_SIZE
import hu.netsurf.erp.PhotoTestConstants.PHOTO_FILE_AS_STRING
import hu.netsurf.erp.PhotoTestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.PhotoTestConstants.UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY
import hu.netsurf.erp.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.testobject.ProductTestObject.Companion.product1
import hu.netsurf.erp.testobject.ProductTestObject.Companion.product1WithPhoto
import hu.netsurf.erp.util.FileUtils
import hu.netsurf.erp.util.FileValidator
import hu.netsurf.erp.wrapper.PhotoFile
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.IOException

class ProductPhotoServiceTests {
    private val productService: ProductService = mockk()
    private val fileUtils: FileUtils = mockk()
    private val fileValidator: FileValidator = mockk()
    private val productPhotoService: ProductPhotoService = ProductPhotoService(productService, fileUtils, fileValidator)
    private val photoFile: PhotoFile = mockk()

    @BeforeEach
    fun setup() {
        justRun { fileValidator.validate(any()) }

        every { photoFile.asString() } returns PHOTO_FILE_AS_STRING
    }

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            fileUtils.readAllBytes(any(), any())
        } returns ByteArray(FILE_SIZE)

        val result = productPhotoService.getProductPhoto(PHOTO_FILE_NAME)
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getProductPhoto test unhappy path`() {
        every {
            fileUtils.readAllBytes(any(), any())
        } throws IOException()

        assertThrows<ProductPhotoNotFoundException> {
            productPhotoService.getProductPhoto(PHOTO_FILE_NAME)
        }
    }

    @Test
    fun `uploadProductPhoto test happy path`() {
        every { productService.getProduct(1) } returns product1()
        every {
            fileUtils.createPhotoUploadsDirectoryStructure(any())
        } returns UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY
        every { fileUtils.storePhoto(any(), any()) } returns PHOTO_FILE_NAME
        every { productService.updateProduct(any()) } returns product1WithPhoto()

        val result = productPhotoService.uploadProductPhoto(1, photoFile)
        assertFalse(result.isNullOrBlank())
    }

    @Test
    fun `uploadProductPhoto test unhappy path`() {
        every { productService.getProduct(1) } returns product1WithPhoto()

        assertThrows<ProductAlreadyHasPhotoUploadedException> {
            productPhotoService.uploadProductPhoto(1, photoFile)
        }
    }
}

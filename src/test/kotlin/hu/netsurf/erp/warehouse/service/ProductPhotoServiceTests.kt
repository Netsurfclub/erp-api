package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.TestConstants.CONTENT_TYPE_IMAGE_JPEG
import hu.netsurf.erp.TestConstants.MULTIPART_FILE_SIZE
import hu.netsurf.erp.TestConstants.ORIGINAL_FILE_NAME
import hu.netsurf.erp.TestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.TestConstants.UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY
import hu.netsurf.erp.testobject.ProductTestObject
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.warehouse.util.FileUtils
import hu.netsurf.erp.warehouse.util.FileValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

class ProductPhotoServiceTests {
    private val productService: ProductService = mockk()
    private val fileUtils: FileUtils = mockk()
    private val fileValidator: FileValidator = mockk()
    private val productPhotoService: ProductPhotoService = ProductPhotoService(productService, fileUtils, fileValidator)
    private val multipartFile: MultipartFile = mockk<MultipartFile>()

    @BeforeEach
    fun setup() {
        justRun { fileValidator.validate(any()) }

        every { multipartFile.originalFilename } returns ORIGINAL_FILE_NAME
        every { multipartFile.size } returns MULTIPART_FILE_SIZE.toLong()
        every { multipartFile.contentType } returns CONTENT_TYPE_IMAGE_JPEG
    }

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            fileUtils.readAllBytes(any(), any())
        } returns ByteArray(MULTIPART_FILE_SIZE)

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
        every { productService.getProduct(1) } returns ProductTestObject.product1()
        every {
            fileUtils.createPhotoUploadsDirectoryStructure(any())
        } returns UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY
        every { fileUtils.storePhoto(any(), any()) } returns PHOTO_FILE_NAME
        every { productService.updateProduct(any()) } returns ProductTestObject.product1WithPhoto()

        val result = productPhotoService.uploadProductPhoto(1, multipartFile)
        assertFalse(result.isNullOrBlank())
    }

    @Test
    fun `uploadProductPhoto test unhappy path`() {
        every { productService.getProduct(1) } returns ProductTestObject.product1WithPhoto()

        assertThrows<ProductAlreadyHasPhotoUploadedException> {
            productPhotoService.uploadProductPhoto(1, multipartFile)
        }
    }
}

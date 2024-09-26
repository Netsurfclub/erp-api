package hu.netsurf.erp.warehouse.service

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
        justRun { fileValidator.validate() }

        every { multipartFile.originalFilename } returns "file_name.jpeg"
        every { multipartFile.size } returns 10485760
        every { multipartFile.contentType } returns "image/jpeg"
    }

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            fileUtils.readAllBytes()
        } returns ByteArray(10485760)

        val result = productPhotoService.getProductPhoto("file_name.jpeg")
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getProductPhoto test unhappy path`() {
        every {
            fileUtils.readAllBytes()
        } throws IOException()

        assertThrows<ProductPhotoNotFoundException> {
            productPhotoService.getProductPhoto("file_name.jpeg")
        }
    }

    @Test
    fun `uploadProductPhoto test happy path`() {
        every { productService.getProduct(1) } returns ProductTestObject.product1()
        every { fileUtils.createPhotoUploadsDirectoryStructure() } returns "uploads/photos/products/"
        every { fileUtils.storePhoto() } returns "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg"
        every { productService.updateProduct() } returns ProductTestObject.product1WithPhoto()

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

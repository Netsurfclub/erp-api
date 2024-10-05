package hu.netsurf.erp.controller

import hu.netsurf.erp.TestConstants.CONTENT_TYPE_IMAGE_JPEG
import hu.netsurf.erp.TestConstants.MULTIPART_FILE_SIZE
import hu.netsurf.erp.TestConstants.ORIGINAL_FILE_NAME
import hu.netsurf.erp.TestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.exception.ProductNotFoundException
import hu.netsurf.erp.exception.ProductPhotoNotFoundException
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

class ProductPhotoControllerTests {
    private val productPhotoService: ProductPhotoService = mockk()
    private val productPhotoController: ProductPhotoController = ProductPhotoController(productPhotoService)
    private val multipartFile: MultipartFile = mockk<MultipartFile>()

    @BeforeEach
    fun setup() {
        every { multipartFile.originalFilename } returns ORIGINAL_FILE_NAME
        every { multipartFile.size } returns MULTIPART_FILE_SIZE.toLong()
        every { multipartFile.contentType } returns CONTENT_TYPE_IMAGE_JPEG
    }

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            productPhotoService.getProductPhoto(PHOTO_FILE_NAME)
        } returns ByteArray(MULTIPART_FILE_SIZE)

        val result = productPhotoController.getProductPhoto(PHOTO_FILE_NAME)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(CONTENT_TYPE_IMAGE_JPEG, result.headers.contentType.toString())
        assertTrue(result.body.toString().isNotEmpty())
    }

    @Test
    fun `getProductPhoto test unhappy path - product photo not found (HTTP 404)`() {
        every {
            productPhotoService.getProductPhoto(PHOTO_FILE_NAME)
        } throws ProductPhotoNotFoundException(PHOTO_FILE_NAME)

        val result = productPhotoController.getProductPhoto(PHOTO_FILE_NAME)
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
        assertEquals(
            "Fénykép a következő fájlnévvel: 7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg nem található a termékről.",
            result.body.toString(),
        )
    }

    @Test
    fun `getProductPhoto test unhappy path - bad request (HTTP 400)`() {
        val exceptionMessage = "IOException occurred."
        every {
            productPhotoService.getProductPhoto(PHOTO_FILE_NAME)
        } throws IOException(exceptionMessage)

        val result = productPhotoController.getProductPhoto(PHOTO_FILE_NAME)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(exceptionMessage, result.body.toString())
    }

    @Test
    fun `createProductPhoto test happy path`() {
        every {
            productPhotoService.uploadProductPhoto(1, multipartFile)
        } returns PHOTO_FILE_NAME

        val result = productPhotoController.createProductPhoto(1, multipartFile)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(PHOTO_FILE_NAME, result.body.toString())
    }

    @Test
    fun `createProductPhoto test unhappy path - product not found (HTTP 404)`() {
        every {
            productPhotoService.uploadProductPhoto(3, multipartFile)
        } throws ProductNotFoundException(3)

        val result = productPhotoController.createProductPhoto(3, multipartFile)
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
        assertEquals("Termék a következő azonosítóval: #3 nem található.", result.body.toString())
    }

    @Test
    fun `createProductPhoto test unhappy path - product already has photo uploaded (HTTP 400)`() {
        every {
            productPhotoService.uploadProductPhoto(1, multipartFile)
        } throws ProductAlreadyHasPhotoUploadedException(1)

        val result = productPhotoController.createProductPhoto(1, multipartFile)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(
            "Termék a következő azonosítóval: #1 már rendelkezik feltöltött fényképpel.",
            result.body.toString(),
        )
    }
}

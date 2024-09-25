package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.TestConstants.MULTIPART_FILE_SIZE
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.warehouse.service.ProductPhotoService
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
    private val fileName = "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg"

    @BeforeEach
    fun setup() {
        every { multipartFile.originalFilename } returns "file_name.jpeg"
        every { multipartFile.size } returns MULTIPART_FILE_SIZE.toLong()
        every { multipartFile.contentType } returns "image/jpeg"
    }

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            productPhotoService.getProductPhoto(fileName)
        } returns ByteArray(MULTIPART_FILE_SIZE)

        val result = productPhotoController.getProductPhoto(fileName)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals("image/jpeg", result.headers.contentType.toString())
        assertTrue(result.body.toString().isNotEmpty())
    }

    @Test
    fun `getProductPhoto test unhappy path - product photo not found (HTTP 404)`() {
        every {
            productPhotoService.getProductPhoto(fileName)
        } throws ProductPhotoNotFoundException(fileName)

        val result = productPhotoController.getProductPhoto(fileName)
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
            productPhotoService.getProductPhoto(fileName)
        } throws IOException(exceptionMessage)

        val result = productPhotoController.getProductPhoto(fileName)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(exceptionMessage, result.body.toString())
    }

    @Test
    fun `createProductPhoto test happy path`() {
        every {
            productPhotoService.uploadProductPhoto(1, multipartFile)
        } returns fileName

        val result = productPhotoController.createProductPhoto(1, multipartFile)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(fileName, result.body.toString())
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

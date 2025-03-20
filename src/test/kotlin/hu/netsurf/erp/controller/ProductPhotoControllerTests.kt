package hu.netsurf.erp.controller

import hu.netsurf.erp.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG
import hu.netsurf.erp.PhotoTestConstants.FILE_SIZE
import hu.netsurf.erp.PhotoTestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.constant.LoggerConstants.FILE_NAME
import hu.netsurf.erp.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.exception.ProductNotFoundException
import hu.netsurf.erp.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.service.ProductPhotoService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

class ProductPhotoControllerTests {
    private val productPhotoService: ProductPhotoService = mockk()
    private val productPhotoController: ProductPhotoController = ProductPhotoController(productPhotoService)
    private val multipartFile: MultipartFile =
        MockMultipartFile(
            FILE_NAME,
            FILE_NAME,
            CONTENT_TYPE_IMAGE_JPEG,
            ByteArray(1024),
        )

    @Test
    fun `getProductPhoto test happy path`() {
        every {
            productPhotoService.getProductPhoto(any())
        } returns ByteArray(FILE_SIZE)

        val result = productPhotoController.getProductPhoto(PHOTO_FILE_NAME)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(CONTENT_TYPE_IMAGE_JPEG, result.headers.contentType.toString())
        assertTrue(result.body is ByteArray)
    }

    @Test
    fun `getProductPhoto test unhappy path - product photo not found (HTTP 404)`() {
        every {
            productPhotoService.getProductPhoto(any())
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
            productPhotoService.getProductPhoto(any())
        } throws IOException(exceptionMessage)

        val result = productPhotoController.getProductPhoto(PHOTO_FILE_NAME)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(exceptionMessage, result.body)
    }

    @Test
    fun `createProductPhoto test happy path`() {
        every {
            productPhotoService.uploadProductPhoto(any(), any())
        } returns PHOTO_FILE_NAME

        val result = productPhotoController.createProductPhoto(1, multipartFile)
        assertEquals(HttpStatus.OK, result.statusCode)
        assertEquals(PHOTO_FILE_NAME, result.body)
    }

    @Test
    fun `createProductPhoto test unhappy path - product not found (HTTP 404)`() {
        every {
            productPhotoService.uploadProductPhoto(any(), any())
        } throws ProductNotFoundException(3)

        val result = productPhotoController.createProductPhoto(3, multipartFile)
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
        assertEquals("Termék a következő azonosítóval: #3 nem található.", result.body)
    }

    @Test
    fun `createProductPhoto test unhappy path - product already has photo uploaded (HTTP 400)`() {
        every {
            productPhotoService.uploadProductPhoto(any(), any())
        } throws ProductAlreadyHasPhotoUploadedException(1)

        val result = productPhotoController.createProductPhoto(1, multipartFile)
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(
            "Termék a következő azonosítóval: #1 már rendelkezik feltöltött fényképpel.",
            result.body,
        )
    }
}

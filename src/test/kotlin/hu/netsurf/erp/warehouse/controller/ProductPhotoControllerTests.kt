package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG
import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.FILE_SIZE
import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.PHOTO_FILE_NAME
import hu.netsurf.erp.warehouse.exception.ProductAlreadyHasPhotoUploadedException
import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.exception.ProductPhotoNotFoundException
import hu.netsurf.erp.warehouse.service.ProductPhotoService
import hu.netsurf.erp.warehouse.testobject.MultipartFileTestObject.Companion.multipartFile
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.io.IOException

class ProductPhotoControllerTests {
    private val productPhotoService: ProductPhotoService = mockk()
    private val productPhotoController: ProductPhotoController = ProductPhotoController(productPhotoService)

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

        val result = productPhotoController.createProductPhoto(1, multipartFile())
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals(PHOTO_FILE_NAME, result.body)
    }

    @Test
    fun `createProductPhoto test unhappy path - product not found (HTTP 404)`() {
        every {
            productPhotoService.uploadProductPhoto(any(), any())
        } throws ProductNotFoundException(3)

        val result = productPhotoController.createProductPhoto(3, multipartFile())
        assertEquals(HttpStatus.NOT_FOUND, result.statusCode)
        assertEquals("Termék a következő azonosítóval: #3 nem található.", result.body)
    }

    @Test
    fun `createProductPhoto test unhappy path - product already has photo uploaded (HTTP 400)`() {
        every {
            productPhotoService.uploadProductPhoto(any(), any())
        } throws ProductAlreadyHasPhotoUploadedException(1)

        val result = productPhotoController.createProductPhoto(1, multipartFile())
        assertEquals(HttpStatus.BAD_REQUEST, result.statusCode)
        assertEquals(
            "Termék a következő azonosítóval: #1 már rendelkezik feltöltött fényképpel.",
            result.body,
        )
    }
}

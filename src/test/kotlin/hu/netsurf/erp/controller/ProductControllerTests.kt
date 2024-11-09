package hu.netsurf.erp.controller

import hu.netsurf.erp.service.ProductService
import hu.netsurf.erp.testobject.ProductInputTestObject
import hu.netsurf.erp.testobject.ProductTestObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ProductControllerTests {
    private val productService: ProductService = mockk()
    private val productController: ProductController = ProductController(productService)

    @Test
    fun `products test happy path`() {
        every {
            productService.getProducts()
        } returns listOf(ProductTestObject.product1(), ProductTestObject.product2())

        val result = productController.products()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createProduct test happy path`() {
        every {
            productService.createProduct()
        } returns ProductTestObject.product1()

        val result = productController.createProduct(ProductInputTestObject.productInput1())
        assertEquals(ProductTestObject.product1(), result)
    }
}

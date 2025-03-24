package hu.netsurf.erp.controller

import hu.netsurf.erp.service.ProductService
import hu.netsurf.erp.testobject.ProductInputTestObject.Companion.productInput1
import hu.netsurf.erp.testobject.ProductTestObject.Companion.product1
import hu.netsurf.erp.testobject.ProductTestObject.Companion.product2
import hu.netsurf.erp.util.ProductInputSanitizer
import hu.netsurf.erp.util.ProductInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ProductControllerTests {
    private val productService: ProductService = mockk()
    private val productInputSanitizer: ProductInputSanitizer = mockk()
    private val productInputValidator: ProductInputValidator = mockk()
    private val productController: ProductController =
        ProductController(
            productService,
            productInputSanitizer,
            productInputValidator,
        )

    @Test
    fun `products test happy path`() {
        every {
            productService.getProducts()
        } returns listOf(product1(), product2())

        val result = productController.products()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createProduct test happy path`() {
        every {
            productInputSanitizer.sanitize(any())
        } returns productInput1()
        justRun { productInputValidator.validate(any()) }
        every {
            productService.createProduct(any())
        } returns product1()

        val result = productController.createProduct(productInput1())
        assertEquals(product1(), result)
    }
}

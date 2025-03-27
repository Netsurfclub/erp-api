package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.repository.ProductRepository
import hu.netsurf.erp.warehouse.testobject.ProductTestObject.Companion.product1
import hu.netsurf.erp.warehouse.testobject.ProductTestObject.Companion.product2
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class ProductServiceTests {
    private val productRepository: ProductRepository = mockk()
    private val supplierService: SupplierService = mockk()
    private val productService: ProductService = ProductService(productRepository, supplierService)

    @Test
    fun `getProducts test happy path`() {
        every {
            productRepository.findAll()
        } returns listOf(product1(), product2())

        val result = productService.getProducts()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getProduct test happy path`() {
        every {
            productRepository.findById(any())
        } returns Optional.of(product1())

        assertDoesNotThrow {
            val result = productService.getProduct(1)
            assertNotNull(result)
            assertEquals(1, result.id)
        }
    }

    @Test
    fun `getProduct test unhappy path`() {
        every {
            productRepository.findById(any())
        } returns Optional.empty()

        assertThrows<ProductNotFoundException> {
            productService.getProduct(3)
        }
    }

    @Test
    fun `createProduct test happy path`() {
        every {
            productRepository.save(any())
        } returns product1()
        every {
            supplierService.getSupplier(any())
        } returns supplier1()

        val result = productService.createProduct(product1())
        assertEquals(product1(), result)
    }

    @Test
    fun `updateProduct test happy path`() {
        every {
            productRepository.save(any())
        } returns product1()

        val result = productService.updateProduct(product1())
        assertEquals(product1(), result)
    }
}

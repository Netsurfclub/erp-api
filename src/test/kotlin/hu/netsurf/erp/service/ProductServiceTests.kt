package hu.netsurf.erp.service

import hu.netsurf.erp.exception.ProductNotFoundException
import hu.netsurf.erp.repository.ProductRepository
import hu.netsurf.erp.testobject.ProductInputTestObject
import hu.netsurf.erp.testobject.ProductTestObject
import hu.netsurf.erp.testobject.SupplierTestObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
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
        } returns listOf(ProductTestObject.product1(), ProductTestObject.product2())

        val result = productService.getProducts()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getProduct test happy path`() {
        every {
            productRepository.findById()
        } returns Optional.of(ProductTestObject.product1())

        val result = productService.getProduct(1)
        assertEquals(1, result.id)
    }

    @Test
    fun `getProduct test unhappy path`() {
        every {
            productRepository.findById()
        } returns Optional.empty()

        assertThrows<ProductNotFoundException> {
            productService.getProduct(1)
        }
    }

    @Test
    fun `createProduct test happy path`() {
        every {
            productRepository.save()
        } returns ProductTestObject.product1()
        every {
            supplierService.getSupplier()
        } returns SupplierTestObject.supplier1()

        val result = productService.createProduct(ProductInputTestObject.productInput1())
        assertEquals(ProductTestObject.product1(), result)
    }

    @Test
    fun `updateProduct test happy path`() {
        every {
            productRepository.save()
        } returns ProductTestObject.product1()

        val result = productService.updateProduct(ProductTestObject.product1())
        assertEquals(ProductTestObject.product1(), result)
    }
}

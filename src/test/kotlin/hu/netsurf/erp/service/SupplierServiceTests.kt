package hu.netsurf.erp.service

import hu.netsurf.erp.exception.SupplierNotFoundException
import hu.netsurf.erp.repository.SupplierRepository
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier1
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier2
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.Optional

class SupplierServiceTests {
    private val supplierRepository: SupplierRepository = mockk()
    private val supplierService: SupplierService = SupplierService(supplierRepository)

    @Test
    fun `getSuppliers test happy path`() {
        every {
            supplierRepository.findAll()
        } returns listOf(supplier1(), supplier2())

        val result = supplierService.getSuppliers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getSupplier test happy path`() {
        every {
            supplierRepository.findById(any())
        } returns Optional.of(supplier1())

        assertDoesNotThrow {
            val result = supplierService.getSupplier(1)
            assertNotNull(result)
            assertEquals(1, result.id)
        }
    }

    @Test
    fun `getSupplier test unhappy path`() {
        every {
            supplierRepository.findById(any())
        } returns Optional.empty()

        assertThrows<SupplierNotFoundException> {
            supplierService.getSupplier(3)
        }
    }

    @Test
    fun `createSupplier test happy path`() {
        every {
            supplierRepository.save(any())
        } returns supplier1()
        every {
            supplierRepository.findById(any())
        } returns Optional.of(supplier1())

        val result = supplierService.createSupplier(supplier1())
        assertEquals(supplier1(), result)
    }
}

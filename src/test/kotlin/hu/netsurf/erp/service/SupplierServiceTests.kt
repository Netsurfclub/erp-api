package hu.netsurf.erp.service

import hu.netsurf.erp.exception.SupplierNotFoundException
import hu.netsurf.erp.repository.SupplierRepository
import hu.netsurf.erp.testobject.SupplierTestObject
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
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
        } returns listOf(SupplierTestObject.supplier1(), SupplierTestObject.supplier2())

        val result = supplierService.getSuppliers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `getSupplier test happy path`() {
        every {
            supplierRepository.findById(any())
        } returns Optional.of(SupplierTestObject.supplier1())

        val result = supplierService.getSupplier(1)
        assertEquals(1, result.id)
    }

    @Test
    fun `getSupplier test unhappy path`() {
        every {
            supplierRepository.findById(any())
        } returns Optional.empty()

        assertThrows<SupplierNotFoundException> {
            supplierService.getSupplier(1)
        }
    }

    @Test
    fun `createSupplier test happy path`() {
        every {
            supplierRepository.save(any())
        } returns SupplierTestObject.supplier1()
        every {
            supplierRepository.findById(any())
        } returns Optional.of(SupplierTestObject.supplier1())

        val result = supplierService.createSupplier(SupplierTestObject.supplier1())
        assertEquals(SupplierTestObject.supplier1(), result)
    }
}

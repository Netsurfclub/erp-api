package hu.netsurf.erp.controller

import hu.netsurf.erp.service.SupplierService
import hu.netsurf.erp.testobject.SupplierInputTestObject.Companion.supplierInput1
import hu.netsurf.erp.testobject.SupplierInputTestObject.Companion.supplierInput1WithNullEmail
import hu.netsurf.erp.testobject.SupplierInputTestObject.Companion.supplierInput1WithNullPhone
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier1
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier1WithNullEmail
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier1WithNullPhone
import hu.netsurf.erp.testobject.SupplierTestObject.Companion.supplier2
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SupplierControllerTests {
    private val supplierService: SupplierService = mockk()
    private val supplierController: SupplierController = SupplierController(supplierService)

    @Test
    fun `suppliers test happy path`() {
        every {
            supplierService.getSuppliers()
        } returns listOf(supplier1(), supplier2())

        val result = supplierController.suppliers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createSupplier test happy path`() {
        every {
            supplierService.createSupplier(any())
        } returns supplier1()

        val result = supplierController.createSupplier(supplierInput1())
        assertEquals(supplier1(), result)
    }

    @Test
    fun `createSupplier test happy path - phone is null`() {
        every {
            supplierService.createSupplier(any())
        } returns supplier1WithNullPhone()

        val result = supplierController.createSupplier(supplierInput1WithNullPhone())
        assertEquals(supplier1WithNullPhone(), result)
    }

    @Test
    fun `createSupplier test happy path - email is null`() {
        every {
            supplierService.createSupplier(any())
        } returns supplier1WithNullEmail()

        val result = supplierController.createSupplier(supplierInput1WithNullEmail())
        assertEquals(supplier1WithNullEmail(), result)
    }
}

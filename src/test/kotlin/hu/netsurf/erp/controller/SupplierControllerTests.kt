package hu.netsurf.erp.controller

import hu.netsurf.erp.service.SupplierService
import hu.netsurf.erp.testobject.SupplierInputTestObject
import hu.netsurf.erp.testobject.SupplierTestObject
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
        } returns listOf(SupplierTestObject.supplier1(), SupplierTestObject.supplier2())

        val result = supplierController.suppliers()
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `createSupplier test happy path`() {
        every {
            supplierService.createSupplier()
        } returns SupplierTestObject.supplier1()

        val result = supplierController.createSupplier(SupplierInputTestObject.supplierInput1())
        assertEquals(SupplierTestObject.supplier1(), result)
    }
}

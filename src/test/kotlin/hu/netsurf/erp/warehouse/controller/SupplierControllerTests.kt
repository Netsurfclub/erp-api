package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.service.SupplierService
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier2
import hu.netsurf.erp.warehouse.util.sanitization.SupplierInputSanitizer
import hu.netsurf.erp.warehouse.util.validation.SupplierInputValidator
import hu.netsurf.erp.warehouse.util.sanitization.UpdateSupplierInputSanitizer
import hu.netsurf.erp.warehouse.util.validation.UpdateSupplierInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1 as createSupplierInput1
import hu.netsurf.erp.warehouse.testobject.UpdateSupplierInputTestObject.Companion.input1 as updateSupplierInput1

class SupplierControllerTests {
    private val supplierService: SupplierService = mockk()
    private val supplierInputSanitizer: SupplierInputSanitizer = mockk()
    private val supplierInputValidator: SupplierInputValidator = mockk()
    private val updateSupplierInputSanitizer: UpdateSupplierInputSanitizer = mockk()
    private val updateSupplierInputValidator: UpdateSupplierInputValidator = mockk()

    private val supplierController: SupplierController =
        SupplierController(
            supplierService,
            supplierInputSanitizer,
            supplierInputValidator,
            updateSupplierInputSanitizer,
            updateSupplierInputValidator,
        )

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
            supplierInputSanitizer.sanitize(any())
        } returns createSupplierInput1()
        justRun { supplierInputValidator.validate(any()) }
        every {
            supplierService.createSupplier(any())
        } returns supplier1()

        val result = supplierController.createSupplier(createSupplierInput1())
        assertEquals(supplier1(), result)
    }

    @Test
    fun `updateSupplier test happy path`() {
        every {
            updateSupplierInputSanitizer.sanitize(any())
        } returns updateSupplierInput1()
        justRun { updateSupplierInputValidator.validate(any()) }
        every {
            supplierService.updateSupplier(any())
        } returns supplier1()

        val result = supplierController.updateSupplier(updateSupplierInput1())
        assertEquals(supplier1(), result)
    }
}

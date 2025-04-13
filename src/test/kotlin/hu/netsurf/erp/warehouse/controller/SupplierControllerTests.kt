package hu.netsurf.erp.warehouse.controller

import hu.netsurf.erp.warehouse.service.SupplierService
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithNullEmail
import hu.netsurf.erp.warehouse.testobject.CreateSupplierInputTestObject.Companion.input1WithNullPhone
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1WithNullEmail
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier1WithNullPhone
import hu.netsurf.erp.warehouse.testobject.SupplierTestObject.Companion.supplier2
import hu.netsurf.erp.warehouse.util.sanitization.CreateSupplierInputSanitizer
import hu.netsurf.erp.warehouse.util.validation.CreateSupplierInputValidator
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SupplierControllerTests {
    private val supplierService: SupplierService = mockk()
    private val createSupplierInputSanitizer: CreateSupplierInputSanitizer = mockk()
    private val createSupplierInputValidator: CreateSupplierInputValidator = mockk()

    private val supplierController: SupplierController =
        SupplierController(
            supplierService,
            createSupplierInputSanitizer,
            createSupplierInputValidator,
        )

    @BeforeEach
    fun setup() {
        every {
            createSupplierInputSanitizer.sanitize(any())
        } returns input1()
        justRun { createSupplierInputValidator.validate(any()) }
    }

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

        val result = supplierController.createSupplier(input1())
        assertEquals(supplier1(), result)
    }

    @Test
    fun `createSupplier test happy path - phone is null`() {
        every {
            supplierService.createSupplier(any())
        } returns supplier1WithNullPhone()

        val result = supplierController.createSupplier(input1WithNullPhone())
        assertEquals(supplier1WithNullPhone(), result)
    }

    @Test
    fun `createSupplier test happy path - email is null`() {
        every {
            supplierService.createSupplier(any())
        } returns supplier1WithNullEmail()

        val result = supplierController.createSupplier(input1WithNullEmail())
        assertEquals(supplier1WithNullEmail(), result)
    }
}

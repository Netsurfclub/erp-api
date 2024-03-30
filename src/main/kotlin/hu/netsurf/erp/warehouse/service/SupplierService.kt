package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.SUPPLIER_INPUT_MAPPED_TO_SUPPLIER
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants.SUPPLIER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.SUPPLIER
import hu.netsurf.erp.common.logging.constant.warehouse.LoggerConstants.SUPPLIER_INPUT
import hu.netsurf.erp.common.logging.extension.logInfo
import hu.netsurf.erp.warehouse.exception.SupplierNotFoundException
import hu.netsurf.erp.warehouse.extension.toSupplier
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput
import hu.netsurf.erp.warehouse.repository.SupplierRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SupplierService(private val supplierRepository: SupplierRepository) {
    val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    fun getSuppliers(): List<Supplier> {
        return supplierRepository.findAll()
    }

    fun getSupplier(id: Int): Supplier {
        val supplier = supplierRepository.findById(id)

        if (supplier.isEmpty) {
            throw SupplierNotFoundException(id)
        }

        logInfo(
            SUPPLIER_RETRIEVED_FROM_DATABASE,
            mapOf(SUPPLIER to supplier.get()),
        )

        return supplier.get()
    }

    fun createSupplier(supplierInput: SupplierInput): Supplier {
        val supplier = supplierInput.toSupplier()

        logInfo(
            SUPPLIER_INPUT_MAPPED_TO_SUPPLIER,
            mapOf(
                SUPPLIER_INPUT to supplierInput,
                SUPPLIER to supplier,
            ),
        )

        val savedSupplier = supplierRepository.save(supplier)
        return getSupplier(savedSupplier.id)
    }

    private fun logInfo(
        logEventConstants: LogEventConstants,
        additionalProperties: Map<String, Any>,
    ) {
        logger.logInfo(logEventConstants, additionalProperties)
    }
}

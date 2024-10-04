package hu.netsurf.erp.service

import hu.netsurf.erp.constant.LogEventConstants.SUPPLIERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.SUPPLIER_INPUT_MAPPED_TO_SUPPLIER
import hu.netsurf.erp.constant.LogEventConstants.SUPPLIER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LoggerConstants.SUPPLIER
import hu.netsurf.erp.constant.LoggerConstants.SUPPLIER_INPUT
import hu.netsurf.erp.exception.SupplierNotFoundException
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.extension.toSupplier
import hu.netsurf.erp.model.Supplier
import hu.netsurf.erp.model.SupplierInput
import hu.netsurf.erp.repository.SupplierRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SupplierService(
    private val supplierRepository: SupplierRepository,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getSuppliers(): List<Supplier> {
        logger.logInfo(SUPPLIERS_RETRIEVED_FROM_DATABASE)

        return supplierRepository.findAll()
    }

    fun getSupplier(id: Int): Supplier {
        val supplier = supplierRepository.findById(id)

        if (supplier.isEmpty) {
            throw SupplierNotFoundException(id)
        }

        logger.logInfo(
            SUPPLIER_RETRIEVED_FROM_DATABASE,
            mapOf(SUPPLIER to supplier.get()),
        )

        return supplier.get()
    }

    fun createSupplier(supplierInput: SupplierInput): Supplier {
        val supplier = supplierInput.toSupplier()

        logger.logInfo(
            SUPPLIER_INPUT_MAPPED_TO_SUPPLIER,
            mapOf(
                SUPPLIER_INPUT to supplierInput,
                SUPPLIER to supplier,
            ),
        )

        val savedSupplier = supplierRepository.save(supplier)
        return getSupplier(savedSupplier.id)
    }
}

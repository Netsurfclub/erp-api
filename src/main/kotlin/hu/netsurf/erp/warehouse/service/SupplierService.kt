package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.common.extension.logInfo
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIERS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIER_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.warehouse.constant.LogEventConstants.SUPPLIER_SAVED_TO_DATABASE
import hu.netsurf.erp.warehouse.constant.LoggerConstants.SUPPLIER
import hu.netsurf.erp.warehouse.exception.SupplierNotFoundException
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.repository.SupplierRepository
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

    fun getSupplier(id: Long): Supplier {
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

    fun createSupplier(supplier: Supplier): Supplier {
        val savedSupplier = supplierRepository.save(supplier)

        logger.logInfo(
            SUPPLIER_SAVED_TO_DATABASE,
            mapOf(SUPPLIER to supplier),
        )

        return getSupplier(savedSupplier.id)
    }
}

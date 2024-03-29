package hu.netsurf.erp.warehouse.service

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

        return supplier.get()
    }

    fun createSupplier(supplierInput: SupplierInput): Supplier {
        val supplier = supplierRepository.save(supplierInput.toSupplier())
        return getSupplier(supplier.id)
    }
}

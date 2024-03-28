package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.SupplierNotFoundException
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput
import hu.netsurf.erp.warehouse.repository.SupplierRepository
import hu.netsurf.erp.warehouse.util.mapToSupplier
import org.springframework.stereotype.Service

@Service
class SupplierService(private val supplierRepository: SupplierRepository) {

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
        val supplier = mapToSupplier(supplierInput)
        val savedSupplier = supplierRepository.save(supplier)
        return getSupplier(savedSupplier.id)
    }
}

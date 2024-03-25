package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.SupplierNotFoundException
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.model.SupplierInput
import hu.netsurf.erp.warehouse.repository.SupplierRepository
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
        val supplier = Supplier(
            name = supplierInput.name,
            email = supplierInput.email,
            phone = supplierInput.phone,
        )
        return saveSupplier(supplier)
    }

    private fun saveSupplier(supplier: Supplier): Supplier {
        return supplierRepository.save(supplier)
    }
}

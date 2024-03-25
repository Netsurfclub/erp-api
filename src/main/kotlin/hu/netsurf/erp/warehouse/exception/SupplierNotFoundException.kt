package hu.netsurf.erp.warehouse.exception

class SupplierNotFoundException(id: Int) : NotFoundException("Supplier not found with id: $id.")

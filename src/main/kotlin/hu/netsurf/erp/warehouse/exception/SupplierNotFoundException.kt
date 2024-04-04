package hu.netsurf.erp.warehouse.exception

class SupplierNotFoundException(id: Int) : NotFoundException("Beszállító a következő azonosítóval: #$id nem található.")

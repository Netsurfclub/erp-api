package hu.netsurf.erp.warehouse.exception

class ProductNotFoundException(id: Int) : NotFoundException("Termék a következő azonosítóval: #$id nem található.")

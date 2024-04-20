package hu.netsurf.erp.warehouse.exception

import hu.netsurf.erp.common.exception.NotFoundException

class SupplierNotFoundException(id: Int) : NotFoundException("Beszállító a következő azonosítóval: #$id nem található.")

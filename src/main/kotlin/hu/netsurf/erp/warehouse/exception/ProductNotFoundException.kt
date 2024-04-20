package hu.netsurf.erp.warehouse.exception

import hu.netsurf.erp.common.exception.NotFoundException

class ProductNotFoundException(id: Int) : NotFoundException("Termék a következő azonosítóval: #$id nem található.")

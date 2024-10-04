package hu.netsurf.erp.exception

class SupplierNotFoundException(
    id: Int,
) : NotFoundException("Beszállító a következő azonosítóval: #$id nem található.")

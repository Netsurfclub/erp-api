package hu.netsurf.erp.exception

class ProductNotFoundException(
    id: Int,
) : NotFoundException("Termék a következő azonosítóval: #$id nem található.")

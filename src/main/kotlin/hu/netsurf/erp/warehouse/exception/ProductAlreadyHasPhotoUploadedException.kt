package hu.netsurf.erp.warehouse.exception

class ProductAlreadyHasPhotoUploadedException(
    id: Long,
) : Exception("Termék a következő azonosítóval: #$id már rendelkezik feltöltött fényképpel.")

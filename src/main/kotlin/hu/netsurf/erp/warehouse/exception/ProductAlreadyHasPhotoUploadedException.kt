package hu.netsurf.erp.warehouse.exception

class ProductAlreadyHasPhotoUploadedException(
    id: Int,
) : Exception("Termék a következő azonosítóval: #$id már rendelkezik feltöltött fényképpel.")

package hu.netsurf.erp.exception

class ProductAlreadyHasPhotoUploadedException(
    id: Int,
) : Exception("Termék a következő azonosítóval: #$id már rendelkezik feltöltött fényképpel.")

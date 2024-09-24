package hu.netsurf.erp.warehouse.exception

class InvalidFileExtensionException(
    extension: String,
) : Exception("Hibás fájlformátum: .$extension")

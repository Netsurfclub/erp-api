package hu.netsurf.erp.exception

class InvalidFileExtensionException(
    extension: String,
) : Exception("Hibás fájlformátum: .$extension")

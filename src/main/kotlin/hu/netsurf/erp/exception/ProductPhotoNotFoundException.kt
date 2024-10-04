package hu.netsurf.erp.exception

class ProductPhotoNotFoundException(
    fileName: String,
) : NotFoundException("Fénykép a következő fájlnévvel: $fileName nem található a termékről.")

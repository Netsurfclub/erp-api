package hu.netsurf.erp.warehouse.exception

class ProductPhotoNotFoundException(
    fileName: String,
) : NotFoundException("Fénykép a következő fájlnévvel: $fileName nem található a termékről.")

package hu.netsurf.erp.warehouse.exception

import hu.netsurf.erp.common.exception.NotFoundException

class ProductPhotoNotFoundException(
    fileName: String,
) : NotFoundException("Fénykép a következő fájlnévvel: $fileName nem található a termékről.")

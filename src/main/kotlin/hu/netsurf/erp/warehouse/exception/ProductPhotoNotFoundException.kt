package hu.netsurf.erp.warehouse.exception

class ProductPhotoNotFoundException(
    fileName: String,
) : NotFoundException("Photo of product not found with file name: $fileName.")

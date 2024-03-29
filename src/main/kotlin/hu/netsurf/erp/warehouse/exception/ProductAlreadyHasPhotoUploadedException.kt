package hu.netsurf.erp.warehouse.exception

class ProductAlreadyHasPhotoUploadedException(id: Int) : Exception("Product with id: #$id already has photo uploaded.")

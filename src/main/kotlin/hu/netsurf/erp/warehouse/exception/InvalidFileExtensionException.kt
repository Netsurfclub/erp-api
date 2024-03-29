package hu.netsurf.erp.warehouse.exception

class InvalidFileExtensionException(extension: String) : Exception("Invalid file extension: $extension")

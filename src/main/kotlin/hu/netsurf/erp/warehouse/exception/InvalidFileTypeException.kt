package hu.netsurf.erp.warehouse.exception

class InvalidFileTypeException(fileType: String) : Exception("Invalid file type: $fileType")

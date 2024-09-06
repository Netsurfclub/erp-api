package hu.netsurf.erp.warehouse.exception

class EmptyFileException(
    fileName: String?,
) : Exception("Üres fájl: $fileName")

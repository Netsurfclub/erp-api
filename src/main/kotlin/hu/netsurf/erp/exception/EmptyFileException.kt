package hu.netsurf.erp.exception

class EmptyFileException(
    fileName: String?,
) : Exception("Üres fájl: $fileName")

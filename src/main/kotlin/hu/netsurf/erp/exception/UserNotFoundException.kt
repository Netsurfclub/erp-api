package hu.netsurf.erp.exception

class UserNotFoundException(
    id: Int,
) : NotFoundException("Felhasználó a következő azonosítóval: #$id nem található.")

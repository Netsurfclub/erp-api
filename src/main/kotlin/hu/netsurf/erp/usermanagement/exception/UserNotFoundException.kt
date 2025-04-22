package hu.netsurf.erp.usermanagement.exception

import hu.netsurf.erp.common.exception.NotFoundException

class UserNotFoundException(
    id: Long,
) : NotFoundException("Felhasználó a következő azonosítóval: #$id nem található.")

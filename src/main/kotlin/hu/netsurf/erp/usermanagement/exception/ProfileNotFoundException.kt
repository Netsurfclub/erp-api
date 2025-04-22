package hu.netsurf.erp.usermanagement.exception

import hu.netsurf.erp.common.exception.NotFoundException

class ProfileNotFoundException(
    id: Long,
) : NotFoundException("Felhasználói fiók a következő azonosítóval: #$id nem található.")

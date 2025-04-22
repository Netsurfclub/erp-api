package hu.netsurf.erp.usermanagement.exception

import hu.netsurf.erp.common.exception.NotFoundException

class ProfileAlreadyExistException(
    id: Long,
) : NotFoundException("Felhasználói fiók a következő azonosítóval: #$id már létezik.")

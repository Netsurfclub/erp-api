package hu.netsurf.erp.usermanagement.repository

import hu.netsurf.erp.usermanagement.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long>

package hu.netsurf.erp.usermanagement.repository

import hu.netsurf.erp.usermanagement.model.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepository : JpaRepository<Profile, Int>

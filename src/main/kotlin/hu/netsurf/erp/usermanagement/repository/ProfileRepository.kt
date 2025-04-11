package hu.netsurf.erp.usermanagement.repository

import hu.netsurf.erp.usermanagement.model.Profile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProfileRepository : JpaRepository<Profile, Int> {
    fun findByUserId(userId: Int): Optional<Profile>
}

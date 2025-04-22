package hu.netsurf.erp.usermanagement.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "profiles")
data class Profile(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var username: String = "",
    var password: String = "",
    @OneToOne
    @JoinColumn(name = "user_id")
    var user: User = User(),
    var isDeleted: Boolean = false,
)

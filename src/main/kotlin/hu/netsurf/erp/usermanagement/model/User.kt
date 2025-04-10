package hu.netsurf.erp.usermanagement.model

import hu.netsurf.erp.usermanagement.constant.TableSchemaConstants.TABLE_NAME_USERS
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = TABLE_NAME_USERS)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    var username: String = "",
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var isDeleted: Boolean = false,
)

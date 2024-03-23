package hu.netsurf.erp.warehouse.model

import hu.netsurf.erp.warehouse.constants.TableSchemaConstants.TABLE_NAME_SUPPLIERS
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = TABLE_NAME_SUPPLIERS)
data class Supplier(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String = "",
)

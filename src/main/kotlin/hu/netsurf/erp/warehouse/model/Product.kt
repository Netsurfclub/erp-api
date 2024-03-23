package hu.netsurf.erp.warehouse.model

import hu.netsurf.erp.warehouse.constants.TableSchemaConstants.COLUMN_NAME_SUPPLIER_ID
import hu.netsurf.erp.warehouse.constants.TableSchemaConstants.TABLE_NAME_PRODUCTS
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = TABLE_NAME_PRODUCTS)
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    val name: String = "",

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_ID)
    val supplier: Supplier = Supplier(),

    val price: Double = 0.0,

    val unit: String = "",

    val photo: String? = null,

    val onStock: Int = 0,
)
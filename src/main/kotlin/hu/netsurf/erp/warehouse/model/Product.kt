package hu.netsurf.erp.warehouse.model

import hu.netsurf.erp.warehouse.constant.TableSchemaConstants.COLUMN_NAME_SUPPLIER_ID
import hu.netsurf.erp.warehouse.constant.TableSchemaConstants.TABLE_NAME_PRODUCTS
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
    var id: Int = 0,
    var name: String = "",
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = COLUMN_NAME_SUPPLIER_ID)
    var supplier: Supplier = Supplier(),
    var price: Double = 0.0,
    var unit: String = "",
    var photo: String? = null,
    var onStock: Int = 0,
)

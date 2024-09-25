package hu.netsurf.erp.usermanagement.model

import hu.netsurf.erp.warehouse.model.Product

data class CreateProductResponse(
    val product: Product,
    val errorMessage: String = "",
)

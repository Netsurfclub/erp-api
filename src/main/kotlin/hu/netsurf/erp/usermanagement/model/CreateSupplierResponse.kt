package hu.netsurf.erp.usermanagement.model

import hu.netsurf.erp.warehouse.model.Supplier

data class CreateSupplierResponse(
    val supplier: Supplier,
    val errorMessage: String = "",
)

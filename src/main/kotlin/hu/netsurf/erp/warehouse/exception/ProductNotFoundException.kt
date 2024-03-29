package hu.netsurf.erp.warehouse.exception

class ProductNotFoundException(id: Int) : NotFoundException("Product not found with id: #$id.")

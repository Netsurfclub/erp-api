package hu.netsurf.erp.warehouse.exception

class ProductNotFoundException(id: Int) : Exception("Product not found with id: $id.")

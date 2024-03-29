package hu.netsurf.erp.warehouse.repository

import hu.netsurf.erp.warehouse.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int>

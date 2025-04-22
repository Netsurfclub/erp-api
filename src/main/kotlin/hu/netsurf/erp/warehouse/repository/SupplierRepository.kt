package hu.netsurf.erp.warehouse.repository

import hu.netsurf.erp.warehouse.model.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SupplierRepository : JpaRepository<Supplier, Long>

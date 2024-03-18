package hu.netsurf.erp.warehouse.repository

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.Supplier
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {
    fun getProducts(): List<Product> {
        // TODO: Clean up mock data after database connection set up.

        val supplier = Supplier(id = 1, name = "ACME Corp.")

        val product1 =
            Product(
                id = 1,
                name = "Product#1",
                supplier = supplier,
                price = 10.00,
                unit = "darab",
                photo = null,
                onStock = 10,
            )

        val product2 =
            Product(
                id = 2,
                name = "Product#2",
                supplier = supplier,
                price = 20.00,
                unit = "darab",
                photo = null,
                onStock = 100,
            )

        val product3 =
            Product(
                id = 3,
                name = "Product#3",
                supplier = supplier,
                price = 50.00,
                unit = "darab",
                photo = null,
                onStock = 1,
            )

        return listOf(product1, product2, product3)
    }
}

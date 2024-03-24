package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
import hu.netsurf.erp.warehouse.model.Supplier
import hu.netsurf.erp.warehouse.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    val logger: Logger = LoggerFactory.getLogger(ProductService::class.java)

    fun getProducts(): List<Product> {
        return productRepository.findAll()
    }

    fun getProduct(id: Int): Product {
        val product = productRepository.findById(id)

        if (product.isEmpty) {
            throw ProductNotFoundException(id)
        }

        return product.get()
    }

    fun createProduct(productInput: ProductInput): Product {
        val product = Product(
            name = productInput.name,
            supplier = Supplier(
                id = productInput.supplierId,
            ),
            price = productInput.price,
            unit = productInput.unit,
            onStock = productInput.onStock,
        )
        return saveProduct(product)
    }

    fun updateProduct(product: Product): Product {
        return saveProduct(product)
    }

    private fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }
}

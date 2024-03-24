package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

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

    fun createProduct(product: Product): Product {
        return saveProduct(product)
    }

    fun updateProduct(product: Product): Product {
        return saveProduct(product)
    }

    private fun saveProduct(product: Product): Product {
        return productRepository.save(product)
    }
}

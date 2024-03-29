package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.exception.ProductNotFoundException
import hu.netsurf.erp.warehouse.extension.toProduct
import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.model.ProductInput
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
        val product = productRepository.save(productInput.toProduct())
        return getProduct(product.id)
    }

    fun updateProduct(product: Product): Product {
        return productRepository.save(product)
    }
}

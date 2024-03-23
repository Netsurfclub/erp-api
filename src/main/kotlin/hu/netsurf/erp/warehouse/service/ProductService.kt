package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.model.Product
import hu.netsurf.erp.warehouse.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun getProducts(): List<Product> {
        return productRepository.findAll()
    }
}

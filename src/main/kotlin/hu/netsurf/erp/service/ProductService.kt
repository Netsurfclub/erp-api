package hu.netsurf.erp.service

import hu.netsurf.erp.constant.LogEventConstants.PRODUCTS_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.PRODUCT_RETRIEVED_FROM_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.PRODUCT_SAVED_TO_DATABASE
import hu.netsurf.erp.constant.LogEventConstants.PRODUCT_UPDATED_IN_DATABASE
import hu.netsurf.erp.constant.LoggerConstants.PRODUCT
import hu.netsurf.erp.constant.LoggerConstants.UPDATED_PRODUCT
import hu.netsurf.erp.exception.ProductNotFoundException
import hu.netsurf.erp.extension.logInfo
import hu.netsurf.erp.model.Product
import hu.netsurf.erp.repository.ProductRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val supplierService: SupplierService,
) {
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun getProducts(): List<Product> {
        logger.logInfo(PRODUCTS_RETRIEVED_FROM_DATABASE)

        return productRepository.findAll()
    }

    fun getProduct(id: Int): Product {
        val product = productRepository.findById(id)

        if (product.isEmpty) {
            throw ProductNotFoundException(id)
        }

        logger.logInfo(
            PRODUCT_RETRIEVED_FROM_DATABASE,
            mapOf(PRODUCT to product.get()),
        )

        return product.get()
    }

    fun createProduct(product: Product): Product {
        val savedProduct = productRepository.save(product)

        logger.logInfo(
            PRODUCT_SAVED_TO_DATABASE,
            mapOf(
                PRODUCT to product,
            ),
        )

        savedProduct.supplier = supplierService.getSupplier(product.supplier.id)
        return savedProduct
    }

    fun updateProduct(product: Product): Product {
        val updatedProduct = productRepository.save(product)

        logger.logInfo(
            PRODUCT_UPDATED_IN_DATABASE,
            mapOf(
                PRODUCT to product,
                UPDATED_PRODUCT to updatedProduct,
            ),
        )

        return updatedProduct
    }
}

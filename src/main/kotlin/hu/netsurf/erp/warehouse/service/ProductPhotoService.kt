package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.model.Product
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductPhotoService : PhotoService<MultipartFile, Product> {

    override fun uploadPhoto(file: MultipartFile): Product {
        println("Uploading photo...")
        return Product()
    }
}

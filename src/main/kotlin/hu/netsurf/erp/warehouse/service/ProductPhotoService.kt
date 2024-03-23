package hu.netsurf.erp.warehouse.service

import hu.netsurf.erp.warehouse.model.ProductPhoto
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ProductPhotoService : PhotoService<MultipartFile, ProductPhoto> {

    override fun uploadPhoto(file: MultipartFile): ProductPhoto {
        return ProductPhoto(
            name = file.name,
            originalName = file.originalFilename,
            size = file.size,
            contentType = file.contentType,
        )
    }
}

package hu.netsurf.erp.warehouse.testobject

import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.CONTENT_TYPE_IMAGE_JPEG
import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.FILE_NAME_JPG
import hu.netsurf.erp.warehouse.constant.PhotoTestConstants.FILE_SIZE
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile

class MultipartFileTestObject {
    companion object {
        fun multipartFile(): MultipartFile = build()

        private fun build(
            fileName: String = FILE_NAME_JPG,
            originalFilename: String = FILE_NAME_JPG,
            contentType: String = CONTENT_TYPE_IMAGE_JPEG,
            inputStream: ByteArray = ByteArray(FILE_SIZE),
        ): MultipartFile =
            MockMultipartFile(
                fileName,
                originalFilename,
                contentType,
                inputStream,
            )
    }
}

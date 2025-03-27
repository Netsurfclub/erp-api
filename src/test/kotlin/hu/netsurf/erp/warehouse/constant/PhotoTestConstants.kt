package hu.netsurf.erp.warehouse.constant

object PhotoTestConstants {
    const val FILE_SIZE = 10485760
    const val JPG = "jpg"
    const val JPEG = "jpeg"
    const val PNG = "png"
    const val BMP = "bmp"
    const val TXT = "txt"
    val ALLOWED_EXTENSIONS = listOf(JPG, JPEG, PNG, BMP)
    const val CONTENT_TYPE_IMAGE_JPEG = "image/$JPEG"
    const val FILE_NAME_JPG = "file_name.$JPG"
    const val FILE_NAME_JPEG = "file_name.$JPEG"
    const val FILE_NAME_PNG = "file_name.$PNG"
    const val FILE_NAME_BMP = "file_name.$BMP"
    const val INVALID_FILE_NAME = "file_name.txt"
    const val PHOTO_FILE_NAME = "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg"
    const val PHOTO_FILE_AS_STRING = "{originalFileName=$JPEG size=$FILE_SIZE contentType=$CONTENT_TYPE_IMAGE_JPEG}"
    const val UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY = "uploads/photos/products/"
}

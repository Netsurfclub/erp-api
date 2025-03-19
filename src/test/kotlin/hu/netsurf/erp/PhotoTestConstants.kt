package hu.netsurf.erp

object PhotoTestConstants {
    const val FILE_SIZE = 10485760
    private const val JPG = "jpg"
    private const val JPEG = "jpeg"
    private const val PNG = "png"
    private const val BMP = "bmp"
    private const val TXT = "txt"
    const val CONTENT_TYPE_IMAGE_JPEG = "image/jpeg"
    const val FILE_NAME = "file_name.jpeg"
    const val INVALID_ORIGINAL_FILE_EXTENSION = TXT
    const val ORIGINAL_FILE_NAME = FILE_NAME
    const val ORIGINAL_FILE_EXTENSION = JPEG
    const val INVALID_ORIGINAL_FILE_NAME = "file_name.txt"
    const val PHOTO_FILE_NAME = "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg"
    const val PHOTO_FILE_AS_STRING = "{originalFileName=${ORIGINAL_FILE_NAME} size=${FILE_SIZE} contentType=${CONTENT_TYPE_IMAGE_JPEG}}"
    const val UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY = "uploads/photos/products/"
    val ALLOWED_EXTENSIONS = listOf(JPG, JPEG, PNG, BMP)
}

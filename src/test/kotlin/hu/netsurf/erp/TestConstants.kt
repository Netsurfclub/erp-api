package hu.netsurf.erp

object TestConstants {
    const val EMPTY_STRING = ""
    const val MULTIPART_FILE_SIZE = 10485760
    const val CONTENT_TYPE_IMAGE_JPEG = "image/jpeg"
    const val ORIGINAL_FILE_NAME = "file_name.jpeg"
    const val INVALID_ORIGINAL_FILE_NAME = "file_name.txt"
    const val PHOTO_FILE_NAME = "7a759fbb-39d8-4b3b-af57-4266980901dc.jpeg"
    const val UPLOADS_DIRECTORY_WITH_PHOTOS_SUBDIRECTORY_AND_CUSTOM_SUBDIRECTORY = "uploads/photos/products/"
    val ALLOWED_EXTENSIONS = listOf("jpg", "jpeg", "png", "bmp")
    const val PRODUCT_1_NAME = "Product#1"
    const val PRODUCT_1_UNIT = "pieces"
    const val PRODUCT_2_NAME = "Product#2"
    const val SUPPLIER_1_NAME = "Supplier#1"
    const val SUPPLIER_1_PHONE = "+36 (50) 132-35-66"
    const val SUPPLIER_1_EMAIL = "supplier1@test.com"
    const val SUPPLIER_2_NAME = "Supplier#2"
    const val SUPPLIER_2_PHONE = "555456"
    const val SUPPLIER_2_EMAIL = "supplier2@test.com"
    const val SUPPLIER_LONG_NAME =
        "acmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacme" +
            "acmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacme" +
            "acmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacme"
    const val SUPPLIER_LONG_EMAIL = "acmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacmeacme@test.com"
    const val SUPPLIER_LONG_PHONE = "+10 (20) 300-400-500-600-700"
    const val SUPPLIER_INVALID_EMAIL = "acmetest.com"
    const val PASSWORD = "pAsSwOrD"
    const val INVALID_PASSWORD = "pAsSwOrD1"
    const val LONG_PASSWORD = "pAsSwOrDpAsSwOrD"
    const val INVALID_CONFIRM_PASSWORD = "CoNfIrMpAsSwOrD"
    const val NEW_PASSWORD = "NeWpAsSwOrD"
    const val INVALID_NEW_PASSWORD = "NeWpAsSwOrD1"
    const val USERNAME_1 = "jbence"
    const val USERNAME_2 = "jgabor"
    const val LONG_USERNAME_1 =
        "jbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbence" +
            "jbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbencejbence" +
            "jbencejbencejbencejbencejben"
    const val FIRST_NAME_1 = "Bence"
    const val INVALID_FIRST_NAME_1 = "bence"
    const val INVALID_FIRST_NAME_STARTS_WITH_NUMBER = "1firstName"
    const val FIRST_NAME_2 = "Gábor"
    const val LONG_FIRST_NAME_1 =
        "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
            "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
            "BenceBenceBenceBenceBenceBenceB"
    const val LAST_NAME_1 = "Juhász"
    const val INVALID_LAST_NAME_1 = "juhász"
    const val INVALID_LAST_NAME_STARTS_WITH_NUMBER = "1lastName"
    const val LONG_LAST_NAME_1 =
        "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
            "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
            "JuhászJuhászJuhászJuhászJuhászJuhá"
    const val EMAIL_1 = "bjuhasz@netsurfclub.hu"
    const val EMAIL_2 = "gjuhasz@netsurfclub.hu"
    const val INVALID_EMAIL_1 = "bjuhasznetsurfclub.hu"
    const val LONG_EMAIL_1 = "bjuhasz@netsurfclub.hubjuhasz@netsurfclub.hubjuhasz@netsurfclub.hubjuhasz@ne"
    const val INPUT = "input"
    const val USERNAME_INPUT = "username"
    const val PASSWORD_INPUT = "password"
    const val CONFIRM_PASSWORD_INPUT = "confirmPassword"
    const val FIRST_NAME_INPUT = "firstName"
    const val LAST_NAME_INPUT = "lastName"
    const val EMAIL_INPUT = "email"
    const val NAME_INPUT = "name"
    const val PHONE_INPUT = "phone"
}

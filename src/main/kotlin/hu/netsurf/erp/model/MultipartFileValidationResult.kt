package hu.netsurf.erp.model

class MultipartFileValidationResult private constructor(
    val message: String,
) {
    companion object {
        fun emptyFile(fileName: String?): MultipartFileValidationResult = MultipartFileValidationResult("Üres fájl: $fileName")

        fun invalidFileExtension(extension: String): MultipartFileValidationResult =
            MultipartFileValidationResult("Hibás fájlformátum: .$extension")

        fun success(): MultipartFileValidationResult = MultipartFileValidationResult("Validation success.")
    }
}

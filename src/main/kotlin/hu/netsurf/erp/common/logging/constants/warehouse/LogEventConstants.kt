package hu.netsurf.erp.common.logging.constants.warehouse

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
    UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED(
        eventName = "erp-api:productPhotoController:uploadPhoto:RequestReceived",
        eventMessage = "Upload Product Photo Request Received",
    ),
    UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadPhoto:SuccessResponse",
        eventMessage = "Upload Product Photo was successful",
    ),
    UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadPhoto:FailureResponse",
        eventMessage = "Upload Product Photo was not successful",
    ),
}

package hu.netsurf.erp.common.logging.constant.warehouse

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
    PRODUCTS_GRAPHQL_QUERY_RECEIVED(
        eventName = "erp-api:productController:products:QueryReceived",
        eventMessage = "Products GraphQL query received",
    ),
    CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:productController:createProducts:MutationReceived",
        eventMessage = "Create product GraphQL mutation received",
    ),
    UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED(
        eventName = "erp-api:productPhotoController:uploadPhoto:RequestReceived",
        eventMessage = "Product photo upload request received",
    ),
    UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadPhoto:SuccessResponse",
        eventMessage = "Product photo upload was successful",
    ),
    UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadPhoto:FailureResponse",
        eventMessage = "Product photo upload was not successful",
    ),
    SUPPLIERS_GRAPHQL_QUERY_RECEIVED(
        eventName = "erp-api:supplierController:suppliers:QueryReceived",
        eventMessage = "Suppliers GraphQL query received",
    ),
    CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:supplierController:createSupplier:MutationReceived",
        eventMessage = "Create supplier GraphQL mutation received",
    ),
    PRODUCT_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:productService:getProduct",
        eventMessage = "Product retrieved from database",
    ),
    SUPPLIER_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:supplierService:getSupplier",
        eventMessage = "Supplier retrieved from database",
    ),
}

package hu.netsurf.erp.common.logging.constant.warehouse

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
    PRODUCTS_GRAPHQL_QUERY_RECEIVED(
        eventName = "erp-api:productController:products:QueryReceived",
        eventMessage = "Products GraphQL query received",
    ),
    PRODUCTS_GRAPHQL_QUERY_SUCCESS_RESPONSE(
        eventName = "erp-api:productController:products:SuccessResponse",
        eventMessage = "Products GraphQL query success response",
    ),
    CREATE_PRODUCT_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:productController:createProducts:MutationReceived",
        eventMessage = "Create product GraphQL mutation received",
    ),
    CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:productController:createProducts:SuccessResponse",
        eventMessage = "Create product GraphQL mutation success response",
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
    PRODUCT_UPDATED_IN_DATABASE(
        eventName = "erp-api:productService:updateProduct",
        eventMessage = "Product updated in database",
    ),
    SUPPLIER_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:supplierService:getSupplier",
        eventMessage = "Supplier retrieved from database",
    ),
    MULTIPART_FILE_VALIDATED_SUCCESSFULLY(
        eventName = "erp-api:productPhotoService:uploadPhoto",
        eventMessage = "Multipart file validated successfully",
    ),
    PRODUCT_PHOTO_UPLOADS_DIRECTORY_CREATED(
        eventName = "erp-api:fileSystemUtils:createPhotoUploadsDirectoryStructure",
        eventMessage = "Product photo uploads directory successfully created on file system",
    ),
    PRODUCT_PHOTO_STORED_ON_FILE_SYSTEM(
        eventName = "erp-api:fileSystemUtils:storePhoto",
        eventMessage = "Product photo stored successfully in product photos uploads directory on file system",
    ),
    PRODUCT_INPUT_MAPPED_TO_PRODUCT(
        eventName = "erp-api:productService:createProduct",
        eventMessage = "Product input mapped to product",
    ),
    SUPPLIER_INPUT_MAPPED_TO_SUPPLIER(
        eventName = "erp-api:productService:createSupplier",
        eventMessage = "Supplier input mapped to supplier",
    ),
}

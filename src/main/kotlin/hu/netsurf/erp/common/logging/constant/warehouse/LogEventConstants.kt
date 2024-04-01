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
        eventName = "erp-api:productController:createProduct:MutationReceived",
        eventMessage = "Create product GraphQL mutation received",
    ),
    CREATE_PRODUCT_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:productController:createProduct:SuccessResponse",
        eventMessage = "Create product GraphQL mutation success response",
    ),
    GET_PRODUCT_PHOTO_REQUEST_RECEIVED(
        eventName = "erp-api:productPhotoController:getProductPhoto:RequestReceived",
        eventMessage = "Product photo retrieval request received",
    ),
    GET_PRODUCT_PHOTO_SUCCESS_RESPONSE(
        eventName = "erp-api:productPhotoController:getProductPhoto:SuccessResponse",
        eventMessage = "Product photo retrieval was successful",
    ),
    GET_PRODUCT_PHOTO_FAILURE_RESPONSE(
        eventName = "erp-api:productPhotoController:getProductPhoto:FailureResponse",
        eventMessage = "Product photo retrieval was not successful",
    ),
    UPLOAD_PRODUCT_PHOTO_REQUEST_RECEIVED(
        eventName = "erp-api:productPhotoController:uploadProductPhoto:RequestReceived",
        eventMessage = "Product photo upload request received",
    ),
    UPLOAD_PRODUCT_PHOTO_SUCCESS_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadProductPhoto:SuccessResponse",
        eventMessage = "Product photo upload was successful",
    ),
    UPLOAD_PRODUCT_PHOTO_FAILURE_RESPONSE(
        eventName = "erp-api:productPhotoController:uploadProductPhoto:FailureResponse",
        eventMessage = "Product photo upload was not successful",
    ),
    SUPPLIERS_GRAPHQL_QUERY_RECEIVED(
        eventName = "erp-api:supplierController:suppliers:QueryReceived",
        eventMessage = "Suppliers GraphQL query received",
    ),
    SUPPLIERS_GRAPHQL_QUERY_SUCCESS_RESPONSE(
        eventName = "erp-api:supplierController:suppliers:SuccessResponse",
        eventMessage = "Suppliers GraphQL query success response",
    ),
    CREATE_SUPPLIER_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:supplierController:createSupplier:MutationReceived",
        eventMessage = "Create supplier GraphQL mutation received",
    ),
    CREATE_SUPPLIER_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:supplierController:createSupplier:SuccessResponse",
        eventMessage = "Create supplier GraphQL mutation success response",
    ),
    PRODUCTS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:productService:getProducts",
        eventMessage = "Products retrieved from database",
    ),
    PRODUCT_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:productService:getProduct",
        eventMessage = "Product retrieved from database",
    ),
    PRODUCT_UPDATED_IN_DATABASE(
        eventName = "erp-api:productService:updateProduct",
        eventMessage = "Product updated in database",
    ),
    SUPPLIERS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:supplierService:getSuppliers",
        eventMessage = "Suppliers retrieved from database",
    ),
    SUPPLIER_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:supplierService:getSupplier",
        eventMessage = "Supplier retrieved from database",
    ),
    MULTIPART_FILE_VALIDATED_SUCCESSFULLY(
        eventName = "erp-api:productPhotoService:uploadPhoto",
        eventMessage = "Multipart file validated successfully",
    ),
    PHOTO_BYTES_READ_FROM_FILE_SYSTEM(
        eventName = "erp-api:fileSystemUtils:readAllBytes",
        eventMessage = "Photo bytes read from file system",
    ),
    PHOTO_UPLOADS_DIRECTORY_CREATED_ON_FILE_SYSTEM(
        eventName = "erp-api:fileSystemUtils:createPhotoUploadsDirectoryStructure",
        eventMessage = "Photo uploads directory successfully created on file system",
    ),
    PHOTO_STORED_ON_FILE_SYSTEM(
        eventName = "erp-api:fileSystemUtils:storePhoto",
        eventMessage = "Photo stored successfully in product photos uploads directory on file system",
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

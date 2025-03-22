package hu.netsurf.erp.constant

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
    MULTIPART_FILE_MAPPED_TO_PHOTO_FILE(
        eventName = "erp-api:productPhotoController:uploadProductPhoto:MultipartFileMappedToPhotoFile",
        eventMessage = "MultipartFile mapped to ProductPhotoFile",
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
    USERS_GRAPHQL_QUERY_RECEIVED(
        eventName = "erp-api:userController:users:QueryReceived",
        eventMessage = "Users GraphQL query received",
    ),
    USERS_GRAPHQL_QUERY_SUCCESS_RESPONSE(
        eventName = "erp-api:userController:users:SuccessResponse",
        eventMessage = "Users GraphQL query success response",
    ),
    CREATE_USER_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:userController:createUser:MutationReceived",
        eventMessage = "Create user GraphQL mutation received",
    ),
    CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:userController:createUser:SuccessResponse",
        eventMessage = "Create user GraphQL mutation success response",
    ),
    UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:userController:updateUserPassword:MutationReceived",
        eventMessage = "Update user password GraphQL mutation received",
    ),
    UPDATE_USER_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:userController:updateUserPassword:SuccessResponse",
        eventMessage = "Update user password GraphQL mutation success response",
    ),
    DELETE_USER_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:userController:deleteUser:MutationReceived",
        eventMessage = "Delete user GraphQL mutation received",
    ),
    DELETE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:userController:deleteUser:SuccessResponse",
        eventMessage = "Delete user GraphQL mutation success response",
    ),
    PHOTO_FILE_VALIDATED_SUCCESSFULLY(
        eventName = "erp-api:productPhotoService:uploadPhoto",
        eventMessage = "PhotoFile validated successfully",
    ),
    PRODUCTS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:productService:getProducts",
        eventMessage = "Products retrieved from database",
    ),
    PRODUCT_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:productService:getProduct",
        eventMessage = "Product retrieved from database",
    ),
    PRODUCT_INPUT_MAPPED_TO_PRODUCT(
        eventName = "erp-api:productController:createProduct",
        eventMessage = "Product input mapped to product",
    ),
    PRODUCT_SAVED_TO_DATABASE(
        eventName = "erp-api:productService:saveProduct",
        eventMessage = "Product saved in database",
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
    SUPPLIER_INPUT_MAPPED_TO_SUPPLIER(
        eventName = "erp-api:supplierController:createSupplier",
        eventMessage = "Supplier input mapped to supplier",
    ),
    SUPPLIER_SAVED_TO_DATABASE(
        eventName = "erp-api:supplierService:saveSupplier",
        eventMessage = "Supplier saved in database",
    ),
    USERS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getUsers",
        eventMessage = "Users retrieved from database",
    ),
    USER_INPUT_MAPPED_TO_USER(
        eventName = "erp-api:userService:createUser",
        eventMessage = "User input mapped to user",
    ),
    USER_SAVED_TO_DATABASE(
        eventName = "erp-api:userService:saveUser",
        eventMessage = "User saved in database",
    ),
    USER_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getUser",
        eventMessage = "User retrieved from database",
    ),
    USER_UPDATED_IN_DATABASE(
        eventName = "erp-api:userService:updateUser",
        eventMessage = "User updated in database",
    ),
    USER_DELETED_FROM_DATABASE(
        eventName = "erp-api:userService:deleteUser",
        eventMessage = "User deleted from database",
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
}

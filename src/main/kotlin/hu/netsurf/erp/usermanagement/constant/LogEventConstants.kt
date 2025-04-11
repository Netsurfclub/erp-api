package hu.netsurf.erp.usermanagement.constant

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
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
    USER_PASSWORD_UPDATED_IN_DATABASE(
        eventName = "erp-api:userService:updateUserPassword",
        eventMessage = "User password updated in database",
    ),
    USER_DELETED_FROM_DATABASE(
        eventName = "erp-api:userService:deleteUser",
        eventMessage = "User deleted from database",
    ),
}

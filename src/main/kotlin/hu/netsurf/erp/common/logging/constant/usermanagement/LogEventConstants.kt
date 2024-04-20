package hu.netsurf.erp.common.logging.constant.usermanagement

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
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
    USERS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getUsers",
        eventMessage = "Users retrieved from database",
    ),
    USER_INPUT_MAPPED_TO_USER(
        eventName = "erp-api:userService:createUser",
        eventMessage = "User input mapped to user",
    ),
}

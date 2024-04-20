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
    USERS_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getUsers",
        eventMessage = "Users retrieved from database",
    ),
}

package hu.netsurf.erp.usermanagement.constant

enum class LogEventConstants(
    val eventName: String,
    val eventMessage: String,
) {
    CREATE_PROFILE_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:profileController:createProfile:MutationReceived",
        eventMessage = "Create profile GraphQL mutation received",
    ),
    UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:profileController:updateProfilePassword:MutationReceived",
        eventMessage = "Update profile password GraphQL mutation received",
    ),
    UPDATE_PROFILE_PASSWORD_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:profileController:updateProfilePassword:SuccessResponse",
        eventMessage = "Update profile password GraphQL mutation success response",
    ),
    DELETE_PROFILE_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:profileController:deleteProfile:MutationReceived",
        eventMessage = "Delete profile GraphQL mutation received",
    ),
    DELETE_PROFILE_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:profileController:deleteProfile:SuccessResponse",
        eventMessage = "Delete profile GraphQL mutation success response",
    ),
    PROFILE_SAVED_TO_DATABASE(
        eventName = "erp-api:profileService:createProfile",
        eventMessage = "Profile saved in database",
    ),
    PROFILE_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getProfile",
        eventMessage = "Profile retrieved from database",
    ),
    PROFILE_PASSWORD_UPDATED_IN_DATABASE(
        eventName = "erp-api:profileService:updateProfilePassword",
        eventMessage = "Profile password updated in database",
    ),
    PROFILE_DELETED_FROM_DATABASE(
        eventName = "erp-api:profileService:deleteProfile",
        eventMessage = "Profile deleted from database",
    ),
    CREATE_USER_GRAPHQL_MUTATION_RECEIVED(
        eventName = "erp-api:userController:createUser:MutationReceived",
        eventMessage = "Create user GraphQL mutation received",
    ),
    CREATE_USER_GRAPHQL_MUTATION_SUCCESS_RESPONSE(
        eventName = "erp-api:userController:createUser:SuccessResponse",
        eventMessage = "Create user GraphQL mutation success response",
    ),
    USER_INPUT_MAPPED_TO_USER(
        eventName = "erp-api:userService:createUser",
        eventMessage = "User input mapped to user",
    ),
    USER_SAVED_TO_DATABASE(
        eventName = "erp-api:userService:createUser",
        eventMessage = "User saved in database",
    ),
    USER_RETRIEVED_FROM_DATABASE(
        eventName = "erp-api:userService:getUser",
        eventMessage = "User retrieved from database",
    ),
}

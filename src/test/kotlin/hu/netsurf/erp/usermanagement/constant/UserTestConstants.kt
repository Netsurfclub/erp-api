package hu.netsurf.erp.usermanagement.constant

object UserTestConstants {
    const val USER_1_FIRST_NAME = "Bence"
    const val USER_1_INVALID_FIRST_NAME_STARTS_WITH_LOWERCASE_CHARACTER = "bence"
    const val USER_1_INVALID_FIRST_NAME_CONTAINS_DIGIT = "B3nce"
    const val USER_1_LONG_FIRST_NAME =
        "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
            "BenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBenceBence" +
            "BenceBenceBenceBenceBenceBenceB"
    const val USER_1_LAST_NAME = "Juhász"
    const val USER_1_INVALID_LAST_NAME_STARTS_WITH_LOWERCASE_CHARACTER = "juhász"
    const val USER_1_INVALID_LAST_NAME_CONTAINS_DIGIT = "Juhas3"
    const val USER_1_LONG_LAST_NAME =
        "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
            "JuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhászJuhász" +
            "JuhászJuhászJuhászJuhászJuhászJuhá"
    const val USER_1_EMAIL = "juhasz.bence@netsurfclub.hu"
    const val USER_1_INVALID_EMAIL = "juhasz.bencenetsurfclub.hu"
    const val USER_1_LONG_EMAIL = "juhasz.bence@netsurfclub.hujuhasz.bence@netsurfclub.hujuhasz.bence@netsurfcl"
}

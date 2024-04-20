package hu.netsurf.erp.common.logging.extension

import hu.netsurf.erp.common.logging.constant.common.LoggerConstants.EMPTY_STRING
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants
import org.slf4j.Logger

fun Logger.logInfo(
    logEventConstants: LogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) {
    val additionalPropertiesFormatted = if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        EMPTY_STRING
    }

    this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} $additionalPropertiesFormatted")
}

fun Logger.logError(logEventConstants: LogEventConstants, exception: Exception) {
    this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: ${exception.message}")
}

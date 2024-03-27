package hu.netsurf.erp.common.logging.extension

import hu.netsurf.erp.common.logging.constants.warehouse.LogEventConstants
import org.slf4j.Logger

fun Logger.logInfo(
    logEventConstants: LogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) {
    val additionalPropertiesFormatted = if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        ""
    }

    this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} $additionalPropertiesFormatted")
}

fun Logger.logError(logEventConstants: LogEventConstants, exception: Exception) {
    this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: ${exception.message}")
}

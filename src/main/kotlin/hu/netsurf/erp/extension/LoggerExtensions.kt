package hu.netsurf.erp.extension

import hu.netsurf.erp.constant.LogEventConstants
import hu.netsurf.erp.constant.LoggerConstants.EMPTY_STRING
import org.slf4j.Logger

fun Logger.logInfo(
    logEventConstants: LogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

fun Logger.logError(
    logEventConstants: LogEventConstants,
    exception: Exception,
) = this.logError(logEventConstants, exception.message)

fun Logger.logError(
    logEventConstants: LogEventConstants,
    errorMessage: String?,
) = this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: $errorMessage")

private fun format(additionalProperties: Map<String, Any>) =
    if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        EMPTY_STRING
    }

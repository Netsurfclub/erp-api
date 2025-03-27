package hu.netsurf.erp.common.extension

import hu.netsurf.erp.common.constant.CommonConstants.EMPTY_STRING
import org.slf4j.Logger

fun Logger.logInfo(
    logEventConstants: hu.netsurf.erp.warehouse.constant.LogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

fun Logger.logError(
    logEventConstants: hu.netsurf.erp.warehouse.constant.LogEventConstants,
    exception: Exception,
) = this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: ${exception.message}")

fun Logger.logInfo(
    logEventConstants: hu.netsurf.erp.usermanagement.constant.LogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

private fun format(additionalProperties: Map<String, Any>) =
    if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        EMPTY_STRING
    }

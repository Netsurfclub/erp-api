package hu.netsurf.erp.common.logging.extension

import hu.netsurf.erp.common.logging.constant.common.LoggerConstants.EMPTY_STRING
import org.slf4j.Logger
import hu.netsurf.erp.common.logging.constant.usermanagement.LogEventConstants as UserManagementLogEventConstants
import hu.netsurf.erp.common.logging.constant.warehouse.LogEventConstants as WarehouseLogEventConstants

fun Logger.logInfo(
    logEventConstants: UserManagementLogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

fun Logger.logInfo(
    logEventConstants: WarehouseLogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

fun Logger.logError(
    logEventConstants: WarehouseLogEventConstants,
    exception: Exception,
) = this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: ${exception.message}")

private fun format(additionalProperties: Map<String, Any>) =
    if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        EMPTY_STRING
    }

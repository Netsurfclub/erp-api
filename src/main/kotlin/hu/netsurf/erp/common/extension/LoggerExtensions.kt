package hu.netsurf.erp.common.extension

import hu.netsurf.erp.common.constant.CommonConstants.EMPTY_STRING
import org.slf4j.Logger
import hu.netsurf.erp.usermanagement.constant.LogEventConstants as UserManagementLogEventConstants
import hu.netsurf.erp.warehouse.constant.LogEventConstants as WarehouseLogEventConstants

fun Logger.logInfo(
    logEventConstants: WarehouseLogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

fun Logger.logError(
    logEventConstants: WarehouseLogEventConstants,
    exception: Exception,
) = this.error("${logEventConstants.eventName} ${logEventConstants.eventMessage}: ${exception.message}")

fun Logger.logInfo(
    logEventConstants: UserManagementLogEventConstants,
    additionalProperties: Map<String, Any> = emptyMap(),
) = this.info("${logEventConstants.eventName} ${logEventConstants.eventMessage} ${format(additionalProperties)}")

private fun format(additionalProperties: Map<String, Any>) =
    if (additionalProperties.isNotEmpty()) {
        "${additionalProperties.map { it.key + " = " + it.value }}"
    } else {
        EMPTY_STRING
    }

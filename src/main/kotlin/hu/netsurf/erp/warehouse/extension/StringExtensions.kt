package hu.netsurf.erp.warehouse.extension

fun String.getExtension(): String = this.split('.')[1].lowercase()

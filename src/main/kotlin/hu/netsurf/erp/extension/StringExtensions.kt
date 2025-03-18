package hu.netsurf.erp.extension

fun String.getExtension(): String = this.split('.')[1].lowercase()

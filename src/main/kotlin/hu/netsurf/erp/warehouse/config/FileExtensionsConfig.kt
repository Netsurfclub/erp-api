package hu.netsurf.erp.warehouse.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file-extensions-config")
class FileExtensionsConfig(
    val allowedExtensions: List<String>,
)

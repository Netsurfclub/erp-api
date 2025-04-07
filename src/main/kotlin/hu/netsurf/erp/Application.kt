package hu.netsurf.erp

import hu.netsurf.erp.usermanagement.config.SecurityConfig
import hu.netsurf.erp.warehouse.config.FileExtensionsConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
    value = [
        SecurityConfig::class,
        FileExtensionsConfig::class,
    ],
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

package hu.netsurf.erp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ErpApiApplication

fun main(args: Array<String>) {
    runApplication<ErpApiApplication>(*args)
}

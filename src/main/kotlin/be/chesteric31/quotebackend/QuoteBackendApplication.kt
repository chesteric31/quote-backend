package be.chesteric31.quotebackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class QuoteBackendApplication

fun main(args: Array<String>) {
    runApplication<QuoteBackendApplication>(*args)
}

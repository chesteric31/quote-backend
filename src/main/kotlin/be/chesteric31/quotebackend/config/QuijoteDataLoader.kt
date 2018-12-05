package be.chesteric31.quotebackend.config

import be.chesteric31.quotebackend.domain.Quote
import be.chesteric31.quotebackend.repository.QuoteRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class QuijoteDataLoader : CommandLineRunner {

    private val log = LoggerFactory.getLogger(QuijoteDataLoader::class.java)

    private var repository: QuoteRepository

    constructor(repository: QuoteRepository) {
        this.repository = repository
    }

    override fun run(vararg args: String?) {
        if (this.repository.count().block() === 0L) {
            var i = 0L
            val bufferedReader = BufferedReader(
                    InputStreamReader(javaClass.classLoader.getResourceAsStream("pg2000.txt")!!))
            Flux.fromStream(
                    bufferedReader.lines().filter { l -> !l.trim().isEmpty() }
                            .map { l -> val quote = Quote(i++, "El Quijote", l)
                                repository.save(quote) }
            ).subscribe { m -> log.info("New quote loaded: {}", m.block()) }
            log.info("Repository contains now {} entries.", repository.count().block());

        }
    }

}
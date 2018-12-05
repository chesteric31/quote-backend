package be.chesteric31.quotebackend.controller

import be.chesteric31.quotebackend.domain.Quote
import be.chesteric31.quotebackend.repository.QuoteRepository
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration


@RestController
class QuoteController {

    private val DELAY_PER_ITEM_MS = 100

    private lateinit var repository: QuoteRepository

    constructor(repository: QuoteRepository) {
        this.repository = repository
    }

    @GetMapping("/quotes")
    fun getQuoteFlux(): Flux<Quote> {
        // If you want to use a shorter version of the Flux, use take(100) at the end of the statement below
        return repository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS.toLong()))
    }

    @GetMapping("/quotes-paged")
    fun getQuoteFlux(@RequestParam(name = "page") page: Int,
                     @RequestParam(name = "size") size: Int): Flux<Quote> {
        return repository.retrieveAllQuotesPaged(PageRequest.of(page, size))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS.toLong()))
    }

}
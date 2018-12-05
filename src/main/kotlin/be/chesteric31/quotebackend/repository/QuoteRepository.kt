package be.chesteric31.quotebackend.repository

import be.chesteric31.quotebackend.domain.Quote
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux


interface QuoteRepository: ReactiveCrudRepository<Quote, String> {

    @Query("{ id: { \$exists: true }}")
    fun retrieveAllQuotesPaged(page: Pageable): Flux<Quote>
}
package guihbc.spring_webflux_example.application.repository

import guihbc.spring_webflux_example.application.repository.entity.PaymentEntity
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Mono

interface PaymentReactiveRepository: ReactiveCrudRepository<PaymentEntity, Long> {
    fun findByIdempotencyKey(idempotencyKey: String): Mono<PaymentEntity>
}

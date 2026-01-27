package guihbc.spring_webflux_example.application.usecases

import guihbc.spring_webflux_example.application.repository.PaymentReactiveRepository
import guihbc.spring_webflux_example.application.repository.entity.PaymentEntity
import guihbc.spring_webflux_example.domain.Payment
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class ProcessPaymentUseCase(private val paymentRepository: PaymentReactiveRepository) {

    fun process(payment: Payment): Mono<Payment> {
        return this.paymentRepository.findByIdempotencyKey(payment.idempotencyKey)
            .flatMap { existingEntity ->
                Mono.just(existingEntity)
            }.switchIfEmpty {
                Mono.defer {
                    val entity: PaymentEntity = payment.toEntity()
                    this.paymentRepository.save<PaymentEntity>(entity)
                }
            }.map { it.toDomain() }
    }
}

package guihbc.spring_webflux_example.application.usecases

import guihbc.spring_webflux_example.application.repository.PaymentReactiveRepository
import guihbc.spring_webflux_example.application.repository.entity.PaymentEntity
import guihbc.spring_webflux_example.domain.Payment
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ProcessPaymentUseCase(private val paymentRepository: PaymentReactiveRepository) {

    fun process(payment: Payment): Mono<Payment> {
        val entity: PaymentEntity = payment.toEntity()
        return this.paymentRepository.save<PaymentEntity>(entity)
            .map { it.toDomain() }
    }
}

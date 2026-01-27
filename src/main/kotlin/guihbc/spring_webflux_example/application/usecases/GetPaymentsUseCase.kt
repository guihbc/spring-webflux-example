package guihbc.spring_webflux_example.application.usecases

import guihbc.spring_webflux_example.application.repository.PaymentReactiveRepository
import guihbc.spring_webflux_example.domain.Payment
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class GetPaymentsUseCase(val paymentRepository: PaymentReactiveRepository) {
    fun getPayments(): Flux<Payment> {
        return this.paymentRepository.findAll().map { it.toDomain() }
    }
}
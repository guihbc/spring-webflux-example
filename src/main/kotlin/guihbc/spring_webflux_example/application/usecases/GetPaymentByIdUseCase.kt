package guihbc.spring_webflux_example.application.usecases

import guihbc.spring_webflux_example.application.repository.PaymentReactiveRepository
import guihbc.spring_webflux_example.domain.Payment
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GetPaymentByIdUseCase(private val paymentRepository: PaymentReactiveRepository) {
    fun getById(id: Long): Mono<Payment> {
        return this.paymentRepository.findById(id)
            .map { it.toDomain() }
    }
}
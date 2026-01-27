package guihbc.spring_webflux_example.adapter.inbound.http

import guihbc.spring_webflux_example.adapter.inbound.http.dto.PaymentResponse
import guihbc.spring_webflux_example.adapter.inbound.http.dto.ProcessPaymentRequest
import guihbc.spring_webflux_example.application.usecases.GetPaymentByIdUseCase
import guihbc.spring_webflux_example.application.usecases.GetPaymentsUseCase
import guihbc.spring_webflux_example.application.usecases.ProcessPaymentUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/payments")
class PaymentController(
    private val processPaymentUseCase: ProcessPaymentUseCase,
    private val getPaymentByIdUseCase: GetPaymentByIdUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase) {

    @PostMapping
    fun processPayment(@RequestBody requestBody: ProcessPaymentRequest): Mono<ResponseEntity<PaymentResponse>> {
        return this.processPaymentUseCase.process(requestBody.toDomain())
            .map { payment -> ResponseEntity.status(HttpStatus.CREATED).body(payment.toResponse()) }
    }

    @GetMapping("/{id}")
    fun getPaymentById(@PathVariable id: Long): Mono<ResponseEntity<PaymentResponse>> {
        return this.getPaymentByIdUseCase.getById(id)
            .map { payment -> ResponseEntity.ok(payment.toResponse()) }
    }

    @GetMapping
    fun getPayments(): Flux<PaymentResponse> {
        return this.getPaymentsUseCase.getPayments()
            .map { it.toResponse() }
    }
}

package guihbc.spring_webflux_example.domain

import guihbc.spring_webflux_example.adapter.inbound.http.dto.PaymentResponse
import guihbc.spring_webflux_example.application.repository.entity.PaymentEntity

data class Payment(
    val id: Long? = null,
    val amount: Int,
    val currency: String,
    val idempotencyKey: String
) {
    fun toEntity(): PaymentEntity = PaymentEntity(
        id = this.id,
        amount = this.amount,
        currency = this.currency,
        idempotencyKey = this.idempotencyKey
    )

    fun toResponse(): PaymentResponse = PaymentResponse(
        id = this.id,
        amount = this.amount,
        currency = this.currency,
        idempotencyKey = this.idempotencyKey
    )
}

package guihbc.spring_webflux_example.adapter.inbound.http.dto

import guihbc.spring_webflux_example.domain.Payment

data class ProcessPaymentRequest(
    val amount: Int,
    val currency: String,
    val idempotencyKey: String
) {
    fun toDomain(): Payment = Payment(
        amount = this.amount,
        currency = this.currency,
        idempotencyKey = this.idempotencyKey
    )
}

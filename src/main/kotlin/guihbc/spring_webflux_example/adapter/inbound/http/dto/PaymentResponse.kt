package guihbc.spring_webflux_example.adapter.inbound.http.dto

data class PaymentResponse(
    val id: Long?,
    val amount: Int,
    val currency: String,
    val idempotencyKey: String
)

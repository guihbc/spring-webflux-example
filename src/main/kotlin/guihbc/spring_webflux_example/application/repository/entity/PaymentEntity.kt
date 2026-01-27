package guihbc.spring_webflux_example.application.repository.entity

import guihbc.spring_webflux_example.domain.Payment
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("payments")
data class PaymentEntity(
    @Id
    val id: Long? = null,
    val amount: Int,
    val currency: String,

    @Column("idempotency_key")
    val idempotencyKey: String
) {
    fun toDomain(): Payment = Payment(
        id = this.id,
        amount = this.amount,
        currency = this.currency,
        idempotencyKey = this.idempotencyKey
    )
}

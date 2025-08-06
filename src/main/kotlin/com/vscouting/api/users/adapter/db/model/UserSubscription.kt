package com.vscouting.api.users.adapter.db.model

import com.vscouting.api.users.domain.enum.SubscriptionStatus
import com.vscouting.api.users.domain.enum.SubscriptionType
import org.hibernate.annotations.Type
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "user_subscriptions")
data class UserSubscription(

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    @Column(columnDefinition = "CHAR(36)")
    val id: UUID? = null,

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    val user: User?,

    @Column(name = "amount", nullable = true)
    val amount: Double?,

    @Column(name = "service_amount", nullable = true)
    val serviceAmount: Double?,

    @Column(name = "amount_diff", nullable = true)
    val amountDiff: Double?,

    @Column(name = "start_date", nullable = true)
    val startDate: LocalDateTime?,

    @Column(name = "modified_date", nullable = true)
    val modifiedDate: LocalDateTime?,

    @Column(name = "end_date", nullable = true)
    val endDate: LocalDateTime?,

    @Column(name = "subscription_type", nullable = false)
    val subscriptionType: SubscriptionType,

    @Column(name = "status")
    val status: SubscriptionStatus,
    
    @Column(name = "leagues")
    val leagues: Integer
)

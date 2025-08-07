package com.vscouting.api.users.domain

import com.vscouting.api.users.domain.enum.SubscriptionStatus
import com.vscouting.api.users.domain.enum.SubscriptionType
import java.time.LocalDateTime
import java.util.UUID

data class UserSubscriptionDTO(
    val id: UUID? = null,
    val amount: Double? = null,
    val amountDiff: Double? = null,
    val serviceAmount: Double? = null,
    val startDate: LocalDateTime? = null,
    val modifiedDate: LocalDateTime? = null,
    val endDate: LocalDateTime? = null,
    val subscriptionType: SubscriptionType? = null,
    val status: SubscriptionStatus,
    val leagues: Integer? = null,
)
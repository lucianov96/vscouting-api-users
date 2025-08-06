package com.vscouting.api.users.adapter.controller.model.response

import com.vscouting.api.users.domain.enum.SubscriptionStatus
import com.vscouting.api.users.domain.enum.SubscriptionType
import java.time.LocalDateTime
import java.util.UUID

data class UserSubscriptionResponse(
    val id: UUID? = null,
    val amount: Double?,
    val startDate: LocalDateTime?,
    val modifiedDate: LocalDateTime?,
    val endDate: LocalDateTime?,
    val subscriptionType: SubscriptionType,
    val status: UserSubscriptionStatusResponse,
    val leagues: Integer
) {
    data class UserSubscriptionStatusResponse(
        val name: SubscriptionStatus,
        val value: String,
        val action: String,
        val description: String?
    )
}
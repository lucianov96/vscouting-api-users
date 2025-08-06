package com.vscouting.api.users.adapter.controller.model.request

import com.vscouting.api.users.domain.enum.SubscriptionStatus
import com.vscouting.api.users.domain.enum.SubscriptionType
import java.util.UUID

data class UserUpdateSubscriptionRequest(
    val id: UUID?,
    val subscriptionType: SubscriptionType,
    val leagues: Integer,
    val amount: Double? = null,
    val serviceAmount: Double? = null,
    val status: SubscriptionStatus,
)
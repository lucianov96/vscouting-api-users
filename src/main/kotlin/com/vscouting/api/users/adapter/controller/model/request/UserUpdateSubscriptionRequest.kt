package com.vscouting.api.users.adapter.controller.model.request

import com.vscouting.api.users.domain.enum.SubscriptionStatus

data class UserUpdateSubscriptionRequest(
    val amount: Double? = null,
    val serviceAmount: Double? = null,
    val status: SubscriptionStatus,
)
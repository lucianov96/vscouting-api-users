package com.vscouting.api.users.adapter.controller.model.request

import com.vscouting.api.users.domain.enum.SubscriptionType

data class UserSubscriptionRequest(
    val subscriptionType: SubscriptionType,
    val leagues: Integer
)
package com.vscouting.api.users.adapter.db.mapper

import com.vscouting.api.users.adapter.db.model.User
import com.vscouting.api.users.adapter.db.model.UserSubscription
import com.vscouting.api.users.domain.UserSubscriptionDTO
import com.vscouting.api.users.domain.enum.SubscriptionStatus
import java.time.LocalDateTime
import java.util.UUID

fun UserSubscription.toDTO() = UserSubscriptionDTO(
    id = id,
    amount = amount,
    startDate = startDate,
    modifiedDate = modifiedDate,
    endDate = endDate,
    subscriptionType = subscriptionType,
    status = status,
    leagues = leagues
)

fun UserSubscriptionDTO.toEntity(userId: UUID) = UserSubscription(
    id = id,
    amount = amount,
    amountDiff = amount?.let { a ->
    serviceAmount?.let { s ->
        a - s
    }},
    serviceAmount = serviceAmount,
    startDate = if (this.status == SubscriptionStatus.ACTIVE) LocalDateTime.now() else null,
    modifiedDate = if (this.status == SubscriptionStatus.ACTIVE) LocalDateTime.now() else null,
    endDate = if (this.status == SubscriptionStatus.ACTIVE) LocalDateTime.now().plusYears(1) else null,
    subscriptionType = subscriptionType!!,
    status = status,
    leagues = leagues!!,
    user = User(id = userId)
)

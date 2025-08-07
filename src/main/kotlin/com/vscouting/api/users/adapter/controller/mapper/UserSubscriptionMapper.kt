package com.vscouting.api.users.adapter.controller.mapper

import com.vscouting.api.users.adapter.controller.model.request.UserSubscriptionRequest
import com.vscouting.api.users.adapter.controller.model.request.UserUpdateSubscriptionRequest
import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.domain.UserSubscriptionDTO
import com.vscouting.api.users.domain.enum.SubscriptionStatus
import java.util.UUID

fun UserSubscriptionDTO.toResponse() = UserSubscriptionResponse(
    id = id,
    amount = amount,
    startDate = startDate,
    modifiedDate = modifiedDate,
    endDate = endDate,
    subscriptionType = subscriptionType!!,
    status = UserSubscriptionResponse.UserSubscriptionStatusResponse(
        name = status,
        value = status.value,
        action = status.action.value,
        description = status.description
    ),
    leagues = leagues!!
)

fun UserSubscriptionRequest.toDTO() = UserSubscriptionDTO(
    subscriptionType = subscriptionType,
    leagues = leagues,
    status = SubscriptionStatus.SUBSCRIPTION_PENDING
)

fun UserUpdateSubscriptionRequest.toDTO(id: UUID) = UserSubscriptionDTO(
    id = id,
    amount = amount,
    serviceAmount = serviceAmount,
    status = status
)
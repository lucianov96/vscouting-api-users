package com.vscouting.api.users.application.port.`in`

import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import java.util.UUID

interface GetUserSubscriptionsPortIn {
    fun execute(userId: UUID): List<UserSubscriptionResponse>
}
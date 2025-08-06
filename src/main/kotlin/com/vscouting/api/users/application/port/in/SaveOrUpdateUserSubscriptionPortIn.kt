package com.vscouting.api.users.application.port.`in`

import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.domain.UserSubscriptionDTO
import java.util.UUID

interface SaveOrUpdateUserSubscriptionPortIn {
    fun execute(userId: UUID, userSubscriptionDTO: UserSubscriptionDTO): UserSubscriptionResponse
}
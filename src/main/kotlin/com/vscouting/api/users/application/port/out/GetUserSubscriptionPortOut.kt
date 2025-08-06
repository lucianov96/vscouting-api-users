package com.vscouting.api.users.application.port.out

import com.vscouting.api.users.domain.UserSubscriptionDTO
import java.util.UUID

interface GetUserSubscriptionPortOut {
    fun getUserSubscription(id: UUID): UserSubscriptionDTO
}
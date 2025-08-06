package com.vscouting.api.users.application.port.out

import com.vscouting.api.users.domain.UserSubscriptionDTO
import java.util.UUID

interface GetUserSubscriptionsPortOut {
    fun getUserSubscriptions(userId: UUID): List<UserSubscriptionDTO>
}
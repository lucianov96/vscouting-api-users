package com.vscouting.api.users.application.usecase

import com.vscouting.api.users.adapter.controller.mapper.toResponse
import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.application.port.`in`.GetUserSubscriptionsPortIn
import com.vscouting.api.users.application.port.out.GetUserSubscriptionsPortOut
import org.springframework.stereotype.Component
import java.util.*

@Component
class GetUserSubscriptionsUseCase(
    private val getUserSubscriptionsPortOut: GetUserSubscriptionsPortOut
): GetUserSubscriptionsPortIn {
    override fun execute(userId: UUID): List<UserSubscriptionResponse> =
        getUserSubscriptionsPortOut.getUserSubscriptions(userId).map {
            it.toResponse()
        }


}
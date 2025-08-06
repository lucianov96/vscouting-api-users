package com.vscouting.api.users.application.usecase

import com.vscouting.api.users.adapter.controller.mapper.toResponse
import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.application.port.`in`.SaveOrUpdateUserSubscriptionPortIn
import com.vscouting.api.users.application.port.out.SaveOrUpdateUserSubscriptionPortOut
import com.vscouting.api.users.domain.UserSubscriptionDTO
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateUserSubscriptionUseCase(
    private val saveOrUpdateUserSubscriptionPortOut: SaveOrUpdateUserSubscriptionPortOut
): SaveOrUpdateUserSubscriptionPortIn {
    override fun execute(userId: UUID, userSubscriptionDTO: UserSubscriptionDTO): UserSubscriptionResponse =
        saveOrUpdateUserSubscriptionPortOut.saveOrUpdateUserSubscription(userId, userSubscriptionDTO).toResponse()


}
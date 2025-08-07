package com.vscouting.api.users.application.usecase

import com.vscouting.api.users.adapter.controller.mapper.toResponse
import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.application.port.`in`.SaveOrUpdateUserSubscriptionPortIn
import com.vscouting.api.users.application.port.out.SaveOrUpdateUserSubscriptionPortOut
import com.vscouting.api.users.application.port.out.GetUserSubscriptionPortOut
import com.vscouting.api.users.domain.UserSubscriptionDTO
import com.vscouting.core.errors.NotFoundException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateUserSubscriptionUseCase(
    private val saveOrUpdateUserSubscriptionPortOut: SaveOrUpdateUserSubscriptionPortOut,
    private val getUserSubscriptionPortOut: GetUserSubscriptionPortOut
): SaveOrUpdateUserSubscriptionPortIn {
    override fun execute(userId: UUID, userSubscriptionDTO: UserSubscriptionDTO): UserSubscriptionResponse {
        val subscriptionDTO = getUserSubscriptionPortOut.getUserSubscription(userSubscriptionDTO.id?:
            throw NotFoundException("There is no subscription without id")
        )
        return saveOrUpdateUserSubscriptionPortOut.saveOrUpdateUserSubscription(
            userId,
            mapFromAnotherDTO(userSubscriptionDTO, subscriptionDTO)
        ).toResponse()
    }

    private fun mapFromAnotherDTO(
        userSubscriptionDTO: UserSubscriptionDTO,
        subscriptionDTO: UserSubscriptionDTO
    ): UserSubscriptionDTO {
        return UserSubscriptionDTO(
            id = userSubscriptionDTO.id ?: subscriptionDTO.id,
            amount = userSubscriptionDTO.amount ?: subscriptionDTO.amount,
            amountDiff = userSubscriptionDTO.amountDiff ?: subscriptionDTO.amountDiff,
            serviceAmount = userSubscriptionDTO.serviceAmount ?: subscriptionDTO.serviceAmount,
            startDate = userSubscriptionDTO.startDate ?: subscriptionDTO.startDate,
            modifiedDate = userSubscriptionDTO.modifiedDate ?: subscriptionDTO.modifiedDate,
            endDate = userSubscriptionDTO.endDate ?: subscriptionDTO.endDate,
            subscriptionType = userSubscriptionDTO.subscriptionType ?: subscriptionDTO.subscriptionType,
            status = userSubscriptionDTO.status ?: subscriptionDTO.status,
            leagues = userSubscriptionDTO.leagues ?: subscriptionDTO.leagues,
        )
    }


}
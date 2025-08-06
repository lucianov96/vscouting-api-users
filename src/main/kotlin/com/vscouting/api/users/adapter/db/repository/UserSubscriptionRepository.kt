package com.vscouting.api.users.adapter.db.repository

import com.vscouting.api.users.adapter.db.mapper.toDTO
import com.vscouting.api.users.adapter.db.mapper.toEntity
import com.vscouting.api.users.application.port.out.SaveOrUpdateUserSubscriptionPortOut
import com.vscouting.api.users.application.port.out.GetUserSubscriptionPortOut
import com.vscouting.api.users.application.port.out.GetUserSubscriptionsPortOut
import com.vscouting.api.users.domain.UserSubscriptionDTO
import com.vscouting.core.errors.NotFoundException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserSubscriptionRepository(
    private val userSubscriptionDbRepository: UserSubscriptionDbRepository
): GetUserSubscriptionsPortOut,
    GetUserSubscriptionPortOut,
    SaveOrUpdateUserSubscriptionPortOut {
    override fun getUserSubscriptions(userId: UUID): List<UserSubscriptionDTO> =
        userSubscriptionDbRepository.findAllByUserId(userId).map {
            it.toDTO()
        }

    override fun saveOrUpdateUserSubscription(userId: UUID, subscriptionDTO: UserSubscriptionDTO): UserSubscriptionDTO =
        userSubscriptionDbRepository.save(subscriptionDTO.toEntity(userId)).toDTO()

    override fun getUserSubscription(id: UUID): UserSubscriptionDTO =
        userSubscriptionDbRepository.findById(id).orElseThrow {
            throw NotFoundException("Subscription with id $id not found")
        }.toDTO()
}

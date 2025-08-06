package com.vscouting.api.users.adapter.controller

import com.vscouting.api.users.adapter.controller.mapper.toDTO
import com.vscouting.api.users.adapter.controller.model.request.UserSubscriptionRequest
import com.vscouting.api.users.adapter.controller.model.request.UserUpdateSubscriptionRequest
import com.vscouting.api.users.adapter.controller.model.response.UserSubscriptionResponse
import com.vscouting.api.users.application.port.`in`.SaveOrUpdateUserSubscriptionPortIn
import com.vscouting.api.users.application.port.`in`.GetUserSubscriptionsPortIn
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users/{userId}/subscriptions")
class UserSubscriptionsController(
    private val getUserSubscriptionsPortIn: GetUserSubscriptionsPortIn,
    @Qualifier("createUserSubscriptionUseCase") private val createUserSubscriptionPortIn: SaveOrUpdateUserSubscriptionPortIn,
    @Qualifier("updateUserSubscriptionUseCase") private val updateUserSubscriptionPortIn: SaveOrUpdateUserSubscriptionPortIn,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getUserSubscriptions(
        @PathVariable userId: UUID
    ): List<UserSubscriptionResponse> = getUserSubscriptionsPortIn.execute(userId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createSubscription(
        @PathVariable userId: UUID,
        @RequestBody userSubscriptionRequest: UserSubscriptionRequest
    ): UserSubscriptionResponse = createUserSubscriptionPortIn.execute(userId, userSubscriptionRequest.toDTO())

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateSubscription(
        @PathVariable userId: UUID,
        @PathVariable id: UUID,
        @RequestBody userUpdateSubscriptionRequest: UserUpdateSubscriptionRequest
    ): UserSubscriptionResponse = updateUserSubscriptionPortIn.execute(
        userId,
        userUpdateSubscriptionRequest.copy(
            id = id
        ).toDTO()
    )

}
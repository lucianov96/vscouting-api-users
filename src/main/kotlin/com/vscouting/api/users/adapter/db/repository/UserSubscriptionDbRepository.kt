package com.vscouting.api.users.adapter.db.repository

import com.vscouting.api.users.adapter.db.model.UserSubscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserSubscriptionDbRepository : JpaRepository<UserSubscription, UUID> {

    fun findAllByUserId(@Param("userId") userId: UUID): List<UserSubscription>

}

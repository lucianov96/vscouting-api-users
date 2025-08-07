package com.vscouting.api.users.domain

import java.time.LocalDate
import java.util.UUID

data class UserDTO(
    val id: UUID,
    val email: String?,
    val username: String?,
    val password: String?,
    val phoneNumber: String?,
    val name: String?,
    val surname: String?,
    val birthDate: LocalDate?,
    val nationality: String?,
    val credentials: UUID? = null,
    val subscriptions: List<UserSubscriptionDTO>? = null,
)
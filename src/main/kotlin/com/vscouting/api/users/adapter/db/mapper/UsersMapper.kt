package com.vscouting.api.users.adapter.db.mapper

import com.vscouting.api.users.adapter.db.model.User
import com.vscouting.api.users.adapter.db.model.UserCredentials
import com.vscouting.api.users.adapter.db.model.UserSubscription
import com.vscouting.api.users.domain.UserDTO

fun User.toDTO() = UserDTO(
    id = id,
    name = name ?: "",
    surname = surname ?: "",
    birthDate = birthDate,
    nationality = nationality ?: "",
    email = credentials?.email ?: "",
    password = credentials?.password ?: "",
    username = credentials?.username ?: "",
    phoneNumber = credentials?.phoneNumber ?: "",
    subscriptions = subscriptions?.mapNotNull { it.toDTO() },
    credentials = credentials?.id
)

fun UserDTO.toUserEntity(): User {
    val user = User(
        id = id,
        name = name,
        surname = surname,
        birthDate = birthDate,
        nationality = nationality,
    )

    val credentials = UserCredentials(
        id = credentials,
        email = email,
        password = password,
        username = username,
        phoneNumber = phoneNumber,
        user = user
    )

    val subscriptions = subscriptions?.map {
        it.toEntity(id)
    }

    user.apply { user.credentials = credentials }
    user.apply { user.subscriptions = subscriptions }
    return user
}
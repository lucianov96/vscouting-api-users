package com.vscouting.api.users.adapter.db.mapper

import com.vscouting.api.users.adapter.db.model.User
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
)
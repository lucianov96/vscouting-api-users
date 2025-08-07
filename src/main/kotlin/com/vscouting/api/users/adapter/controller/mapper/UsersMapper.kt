package com.vscouting.api.users.adapter.controller.mapper

import com.vscouting.api.users.adapter.controller.model.request.UserUpdateRequest
import com.vscouting.api.users.adapter.controller.model.response.UserResponse
import com.vscouting.api.users.domain.UserDTO
import com.vscouting.api.users.domain.enum.CountryCode
import java.util.*

fun UserDTO.toResponse() = UserResponse(
    id = id,
    name = name,
    surname = surname,
    birthDate = birthDate,
    nationality = nationality,
    credentials = UserResponse.CredentialsResponse(
        email = email,
        username = username,
        password = password,
    ),
    phoneNumber = UserResponse.PhoneNumberResponse(
        areaCode = phoneNumber?.let { CountryCode.fromDialCode(phoneNumber.split(" ")[0]) },
        number = phoneNumber
    )
)

fun UserUpdateRequest.toDTO(id: UUID) = UserDTO(
    email = email,
    phoneNumber = phoneNumber,
    name = name,
    surname = surname,
    birthDate = birthDate,
    nationality = nationality,
    id = id,
    username = null,
    password = null,
)
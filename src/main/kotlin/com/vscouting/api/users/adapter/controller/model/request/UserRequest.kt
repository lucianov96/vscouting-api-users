package com.vscouting.api.users.adapter.controller.model.request

import java.time.LocalDate

data class UserUpdateRequest(
    val email: String?,
    val phoneNumber: String?,
    val name: String?,
    val surname: String?,
    val birthDate: LocalDate?,
    val nationality: String?,
)
package com.vscouting.api.users.adapter.controller.model.response

import com.vscouting.api.users.domain.enum.CountryCode
import java.time.LocalDate
import java.util.UUID

data class UserResponse(
    val id: UUID,
    val credentials: CredentialsResponse?,
    val phoneNumber: PhoneNumberResponse?,
    val name: String?,
    val surname: String?,
    val birthDate: LocalDate?,
    val nationality: String?,
) {
    data class CredentialsResponse(
        val email: String?,
        val username: String?,
        val password: String?,
    )
    data class PhoneNumberResponse(
        val areaCode: CountryCode?,
        val number: String?,
    )
}

package com.vscouting.api.users.application.usecase

import com.vscouting.api.users.adapter.controller.mapper.toResponse
import com.vscouting.api.users.adapter.controller.model.response.UserResponse
import com.vscouting.api.users.application.port.`in`.UpdateUserPortIn
import com.vscouting.api.users.application.port.out.GetUserPortOut
import com.vscouting.api.users.application.port.out.UpdateUserPortOut
import com.vscouting.api.users.domain.UserDTO
import org.springframework.stereotype.Component

@Component
class UpdateUserUseCase(
    private val updateUserPortOut: UpdateUserPortOut,
    private val getUserPortOut: GetUserPortOut
): UpdateUserPortIn {
    override fun execute(userDTO: UserDTO): UserResponse {
        val userDbDto = getUserPortOut.getUserById(userDTO.id)
        val updatedUser = mapFromAnotherDTO(userDTO, userDbDto)
        return updateUserPortOut.updateUser(updatedUser).toResponse()
    }

    private fun mapFromAnotherDTO(
        userDTO: UserDTO,
        userDbDTO: UserDTO
    ): UserDTO {
        return UserDTO(
            id = userDTO.id,
            email = userDTO.email ?: userDbDTO.email,
            username = userDTO.username ?: userDbDTO.username,
            password = userDTO.password ?: userDbDTO.password,
            phoneNumber = userDTO.phoneNumber ?: userDbDTO.phoneNumber,
            name = userDTO.name ?: userDbDTO.name,
            surname = userDTO.surname ?: userDbDTO.surname,
            birthDate = userDTO.birthDate ?: userDbDTO.birthDate,
            nationality = userDTO.nationality ?: userDbDTO.nationality,
            subscriptions =  userDbDTO.subscriptions,
            credentials = userDbDTO.credentials
        )
    }

}
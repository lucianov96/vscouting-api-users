package com.vscouting.api.users.application.usecase

import com.vscouting.api.users.adapter.controller.mapper.toResponse
import com.vscouting.api.users.adapter.controller.model.response.UserResponse
import com.vscouting.api.users.application.port.`in`.GetUserPortIn
import com.vscouting.api.users.application.port.out.GetUserPortOut
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class GetUserUseCase(
    private val getUserPortOut: GetUserPortOut
): GetUserPortIn {
    override fun execute(userId: UUID): UserResponse =
        getUserPortOut.getUserById(userId).toResponse()
}
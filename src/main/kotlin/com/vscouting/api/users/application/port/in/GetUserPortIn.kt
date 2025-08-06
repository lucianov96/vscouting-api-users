package com.vscouting.api.users.application.port.`in`

import com.vscouting.api.users.adapter.controller.model.response.UserResponse
import java.util.UUID

interface GetUserPortIn {
    fun execute(userId: UUID): UserResponse
}
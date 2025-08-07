package com.vscouting.api.users.application.port.`in`

import com.vscouting.api.users.adapter.controller.model.response.UserResponse
import com.vscouting.api.users.domain.UserDTO

interface UpdateUserPortIn {
    fun execute(userDTO: UserDTO): UserResponse
}
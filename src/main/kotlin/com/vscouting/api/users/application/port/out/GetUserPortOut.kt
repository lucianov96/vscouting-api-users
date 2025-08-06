package com.vscouting.api.users.application.port.out

import com.vscouting.api.users.domain.UserDTO
import java.util.UUID

interface GetUserPortOut {
    fun getUserById(userId: UUID): UserDTO
}
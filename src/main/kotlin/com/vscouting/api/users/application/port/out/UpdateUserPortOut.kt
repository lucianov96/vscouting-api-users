package com.vscouting.api.users.application.port.out

import com.vscouting.api.users.domain.UserDTO

interface UpdateUserPortOut {
    fun updateUser(userDTO: UserDTO): UserDTO
}
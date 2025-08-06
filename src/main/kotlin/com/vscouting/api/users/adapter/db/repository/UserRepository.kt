package com.vscouting.api.users.adapter.db.repository

import com.vscouting.api.users.adapter.db.mapper.toDTO
import com.vscouting.api.users.application.port.out.GetUserPortOut
import com.vscouting.api.users.domain.UserDTO
import com.vscouting.core.errors.NotFoundException
import org.springframework.stereotype.Component
import java.util.*
import kotlin.jvm.optionals.getOrElse

@Component
class UserRepository(
    private val userDbRepository: UserDbRepository
): GetUserPortOut {
    override fun getUserById(userId: UUID): UserDTO =
        userDbRepository.findById(userId).getOrElse {
            throw NotFoundException("User $userId doesn't exist")
        }.toDTO()
}

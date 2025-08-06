package com.vscouting.api.users.adapter.db.repository

import com.vscouting.api.users.adapter.db.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserDbRepository : JpaRepository<User, UUID>

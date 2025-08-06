package com.vscouting.api.users.adapter.controller

import com.vscouting.api.users.application.port.`in`.GetUserPortIn
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/users")
class UsersController(
    private val getUserPortIn: GetUserPortIn
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getUser(
        @PathVariable id: UUID
    ) = getUserPortIn.execute(id)

}
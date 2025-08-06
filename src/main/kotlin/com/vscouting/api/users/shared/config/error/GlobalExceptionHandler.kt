package com.vscouting.api.users.shared.config.error

import com.vscouting.core.errors.ApplicationErrorException
import com.vscouting.core.errors.response.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.UUID

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationErrorException::class)
    fun handleApplicationErrorException(ex: ApplicationErrorException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            httpStatus = ex.status,
            message = ex.message,
            traceId = UUID.randomUUID()
        )
        return ResponseEntity.status(ex.status).body(errorResponse)
    }
}

package com.vscouting.api.users.shared.config.error

import com.vscouting.core.errors.ApplicationErrorException
import com.vscouting.core.errors.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
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

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        val fullMessage = ex.message ?: ""
        val cleanedMessage = fullMessage.split("; nested exception").firstOrNull() ?: fullMessage

        val errorResponse = ErrorResponse(
            httpStatus = HttpStatus.BAD_REQUEST,
            message = cleanedMessage,
            traceId = UUID.randomUUID()
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}

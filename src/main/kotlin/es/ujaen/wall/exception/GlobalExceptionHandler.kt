package es.ujaen.wall.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(DomainException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleDomainException(ex: DomainException): Map<String, String> =
        mapOf("message" to ex.message!!)

}

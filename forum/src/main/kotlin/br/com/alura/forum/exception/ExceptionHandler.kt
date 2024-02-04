package br.com.alura.forum.exception

import br.com.alura.forum.dto.ApiErrorResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest,
    ): ApiErrorResponseDTO {
        return ApiErrorResponseDTO(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = exception.message!!,
            path = request.servletPath
        )

    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest,
    ): ApiErrorResponseDTO {
        return ApiErrorResponseDTO(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = exception.message!!,
            path = request.servletPath
        )

    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest,
    ): ApiErrorResponseDTO {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors
            .forEach { error ->
                errorMessage.put(error.field, error.defaultMessage)
            }
        return ApiErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = errorMessage.toString(),
            path = request.servletPath
        )

    }

    @ExceptionHandler(UserException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleUser(
        exception: UserException,
        request: HttpServletRequest,
    ): ApiErrorResponseDTO {
        return ApiErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message.toString(),
            path = request.servletPath
        )
    }

    @ExceptionHandler(RoleRegisteredException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleRole(
        exception: RoleRegisteredException,
        request: HttpServletRequest,
    ): ApiErrorResponseDTO {
        return ApiErrorResponseDTO(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message.toString(),
            path = request.servletPath
        )

    }

}
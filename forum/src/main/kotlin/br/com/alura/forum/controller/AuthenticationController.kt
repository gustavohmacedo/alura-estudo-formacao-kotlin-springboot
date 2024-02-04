package br.com.alura.forum.controller

import br.com.alura.forum.dto.AuthenticationRequestDTO
import br.com.alura.forum.dto.AuthenticationResponseDTO
import br.com.alura.forum.dto.UserRequestDTO
import br.com.alura.forum.service.AuthenticationService
import br.com.alura.forum.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {

    @PostMapping("/login")
    fun login(
        @RequestBody @Valid authRequestDTO: AuthenticationRequestDTO,
    ): ResponseEntity<AuthenticationResponseDTO> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(authenticationService.authentication(authRequestDTO))
    }

    @PostMapping("/register-user")
    fun register(
        @RequestBody @Valid userRequestDTO: UserRequestDTO,
    ): ResponseEntity<String> {
        userService.registerUser(userRequestDTO)
        return ResponseEntity
            .status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso!")

    }

}
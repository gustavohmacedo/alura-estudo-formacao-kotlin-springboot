package br.com.alura.forum.service

import br.com.alura.forum.dto.AuthenticationRequestDTO
import br.com.alura.forum.dto.AuthenticationResponseDTO

interface AuthenticationService {

    fun authentication(authRequestDTO: AuthenticationRequestDTO): AuthenticationResponseDTO
}
package br.com.alura.forum.service

import br.com.alura.forum.dto.UserResponseDTO

interface UserService {
    fun getUserById(id: Long): UserResponseDTO
}
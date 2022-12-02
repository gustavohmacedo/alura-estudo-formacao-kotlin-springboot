package br.com.alura.forum.service

import br.com.alura.forum.dto.UserResponseDTO

interface UserService {
    fun getById(id: Long): UserResponseDTO
}
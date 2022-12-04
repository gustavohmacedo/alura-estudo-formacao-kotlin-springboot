package br.com.alura.forum.dto

import br.com.alura.forum.entity.User

data class UserResponseDTO(
    val id: Long,
    val name: String,
    val email: String
)

fun User.toUserResponseDTO() = UserResponseDTO(
    id = this.id!!,
    name = this.name,
    email = this.email
)

fun UserResponseDTO.toUserEntity() = User(
    id = this.id,
    name = this.name,
    email = this.email,
    password = ""
)


package br.com.alura.forum.dto

data class UserRequestDTO(
    val email: String,
    val password: String,
    val name: String? = null,
    val roleId: Long
)
package br.com.alura.forum.dto

data class AuthenticationRequestDTO(
    val email: String,
    val password: String
)
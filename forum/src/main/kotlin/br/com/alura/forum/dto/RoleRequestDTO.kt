package br.com.alura.forum.dto

import br.com.alura.forum.entity.Role

data class RoleRequestDTO(
    val role: String
)

fun RoleRequestDTO.toRoleEntity() = Role(
    name = this.role
)
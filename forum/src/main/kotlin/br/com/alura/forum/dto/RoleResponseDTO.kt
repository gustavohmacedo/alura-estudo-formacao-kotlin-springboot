package br.com.alura.forum.dto

import br.com.alura.forum.entity.Role

data class RoleResponseDTO(
    val roleId: Long? = null,
    val name: String
)

fun Role.toRoleResponseDTO() = RoleResponseDTO(
    roleId = this.id,
    name = this.name
)

fun RoleResponseDTO.toRoleEntity() = Role(
    id = this.roleId,
    name = this.name
)
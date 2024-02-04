package br.com.alura.forum.service

import br.com.alura.forum.dto.RoleResponseDTO

interface RoleService {
    fun saveRole(roleRequest: String): RoleResponseDTO
    fun getRole(roleId: Long): RoleResponseDTO
    fun getAllRole(): List<RoleResponseDTO>
    fun deleteRoleById(roleId: Long)

}
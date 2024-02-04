package br.com.alura.forum.repository

import br.com.alura.forum.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {
    fun findRoleByName(role: String): Role?
}
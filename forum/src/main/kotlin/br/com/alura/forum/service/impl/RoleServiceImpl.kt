package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.RoleResponseDTO
import br.com.alura.forum.dto.toRoleEntity
import br.com.alura.forum.dto.toRoleResponseDTO
import br.com.alura.forum.entity.Role
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.exception.RoleRegisteredException
import br.com.alura.forum.repository.RoleRepository
import br.com.alura.forum.service.RoleService
import org.springframework.stereotype.Service

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository
) : RoleService {

    override fun saveRole(roleRequest: String): RoleResponseDTO {
        verifyIfRoleExistsInDatabase(roleRequest)?.let {
            throw RoleRegisteredException("Role já foi cadastrada")
        }

        val newRole = Role(
            name = roleRequest
        )

        return roleRepository.save(newRole).toRoleResponseDTO()
    }

    override fun getRole(roleId: Long): RoleResponseDTO {
        val optionalRole = roleRepository.findById(roleId)
        if (optionalRole.isEmpty) {
            throw NotFoundException("Role não encontrada")
        }

        return optionalRole.get().toRoleResponseDTO()
    }

    override fun getAllRole(): List<RoleResponseDTO> {
        return roleRepository.findAll()
            .map { role ->
                role.toRoleResponseDTO()
            }
    }

    override fun deleteRoleById(roleId: Long) {
        val role = this.getRole(roleId).toRoleEntity()
        roleRepository.delete(role)

    }

    private fun verifyIfRoleExistsInDatabase(role: String): RoleResponseDTO? {
        return roleRepository.findRoleByName(role)?.toRoleResponseDTO()

    }

}
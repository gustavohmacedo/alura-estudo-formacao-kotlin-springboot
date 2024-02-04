package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.UserRequestDTO
import br.com.alura.forum.dto.UserResponseDTO
import br.com.alura.forum.dto.toRoleEntity
import br.com.alura.forum.dto.toUserResponseDTO
import br.com.alura.forum.entity.User
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.exception.UserException
import br.com.alura.forum.repository.UserRepository
import br.com.alura.forum.service.RoleService
import br.com.alura.forum.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val roleService: RoleService
) : UserService {

    override fun getUserById(id: Long): UserResponseDTO {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            throw NotFoundException("Author not found")
        }

        return user.get().toUserResponseDTO()
    }

    override fun registerUser(userRequestDTO: UserRequestDTO) {
        val userOptional = userRepository.findByEmail(userRequestDTO.email)
        if (userOptional != null) {
            throw UserException("Usuário já está cadastrado!")
        }

        val role = roleService.getRole(userRequestDTO.roleId)

        val encryptedPassword: String = BCryptPasswordEncoder().encode(userRequestDTO.password)

        val user = User(
            email = userRequestDTO.email,
            password = encryptedPassword,
            name = userRequestDTO.name ?: "",
            role = listOf(role.toRoleEntity())
        )

        userRepository.save(user)

    }
}
package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.UserResponseDTO
import br.com.alura.forum.dto.toUserResponseDTO
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.repository.UserRepository
import br.com.alura.forum.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService, UserDetailsService {
    override fun getById(id: Long): UserResponseDTO {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            throw NotFoundException("Author not found")
        }
        return user.get().toUserResponseDTO()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository
            .findByEmail(username) ?: throw NotFoundException("User not found")
        return UserDetailImpl(user)
    }
}
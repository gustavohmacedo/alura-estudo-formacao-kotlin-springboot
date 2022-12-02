package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.UserResponseDTO
import br.com.alura.forum.dto.toUserResponseDTO
import br.com.alura.forum.entity.User
import br.com.alura.forum.repository.UserRepository
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getById(id: Long): UserResponseDTO {
        val user = userRepository.findById(id)
        if (user.isEmpty) {
            throw RuntimeException("Author not found")
        }
        return user.get().toUserResponseDTO()
    }
}
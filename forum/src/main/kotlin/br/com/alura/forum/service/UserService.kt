package br.com.alura.forum.service

import br.com.alura.forum.entity.User
import br.com.alura.forum.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getById(id: Long): User {
        val userOptional: Optional<User> = userRepository.findById(id)
        if (userOptional.isEmpty) {
            RuntimeException("User is not found")
        }
        return userOptional.get()
    }

}
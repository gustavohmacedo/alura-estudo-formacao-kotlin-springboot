package br.com.alura.forum.repository

import br.com.alura.forum.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
}
package br.com.alura.forum.service.impl

import br.com.alura.forum.entity.User
import org.springframework.security.core.userdetails.UserDetails

class UserDetailImpl(
    private val user: User
) : UserDetails {
    //A interface UserDetails é utilizada para identificar uma classe que representa o usuário que será autenticado na aplicacao
    override fun getAuthorities() = user.role

    override fun getPassword() = user.password

    override fun getUsername() = user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}
package br.com.alura.forum.service.impl

import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.dto.AuthenticationRequestDTO
import br.com.alura.forum.dto.AuthenticationResponseDTO
import br.com.alura.forum.repository.UserRepository
import br.com.alura.forum.service.AuthenticationService
import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
    @Lazy
    private val authenticationManager: AuthenticationManager,
    private val userRepository: UserRepository,
    private val jwtUtil: JWTUtil
) : UserDetailsService, AuthenticationService {
    /*Através desta função, o Spring security consulta na base se o usuário existe pelos dados passados à interface UserDetails.*/
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository
            .findByEmail(email) ?: throw UsernameNotFoundException("Username not found")
        return UserDetailImpl(user)
    }

    override fun authentication(authRequestDTO: AuthenticationRequestDTO): AuthenticationResponseDTO {
        val usernamePassword = UsernamePasswordAuthenticationToken(
            authRequestDTO.email,
            authRequestDTO.password
        )

        /*AuthenticationManager faz um encode da senha e compara com o hash da senha do usuario que está na base.
        * Se username e password estiverem corretos, então será retornado o usuário autenticado.*/
        val authenticate = authenticationManager.authenticate(usernamePassword)
        val token = jwtUtil.generateToken(authenticate.principal as UserDetailImpl)

        return AuthenticationResponseDTO(token = token)

    }
}
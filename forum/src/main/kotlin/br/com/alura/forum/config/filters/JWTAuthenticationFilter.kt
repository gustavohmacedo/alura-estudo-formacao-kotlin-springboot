package br.com.alura.forum.config.filters

import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.repository.UserRepository
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTAuthenticationFilter(
    private val userRepository: UserRepository,
    private val jwtUtil: JWTUtil
) : OncePerRequestFilter() {

    //Função para recuperar dados do usuário que estão dentro do token a cada request
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val token: String? = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if (jwt != null) {
            val login = jwtUtil.validateToken(jwt)
            val user = userRepository.findByEmail(login)

            /*Pegando informacoes do usuario para o Spring Security verificar se o usuario tem a Role e/ou esta autenticado
              para acessar rotas que exigem tal.*/
            val authentication = UsernamePasswordAuthenticationToken(user, null, user?.role)
            /*salvando o usuario no contexto da requisicao para que o Spring possa utilizar depois*/
            SecurityContextHolder.getContext().authentication = authentication

        }

        filterChain.doFilter(request, response) //chama o próximo filtro

    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ", ignoreCase = true)
            jwt.replace("Bearer ", "")

        }

    }

}
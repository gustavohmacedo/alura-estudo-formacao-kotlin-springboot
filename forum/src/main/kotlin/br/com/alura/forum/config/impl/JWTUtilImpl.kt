package br.com.alura.forum.config.impl

import br.com.alura.forum.config.JWTUtil
import br.com.alura.forum.service.impl.UserDetailImpl
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset


@Component
class JWTUtilImpl(
    @Value("\${api.security.token.secret}")
    val secret: String
) : JWTUtil {

    override fun generateToken(user: UserDetailImpl): String {
        try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret) // algoritmo de criptografia

            return JWT.create()
                .withIssuer("forum-api") //nome da aplicação emissora do token
                .withSubject(user.username) //usuário resgistrado no token
                .withExpiresAt(generateExpirationDate()) // tempo de expiração do token
                .sign(algorithm) // assinatura para geração do token criptografado

        } catch (ex: JWTCreationException) {
            throw JWTCreationException("Error while generate token", ex)

        }

    }

    //função para validar o token e retornar o usuário, quando o mesmo fizer uma nova requisiçao.
    override fun validateToken(token: String): String {
        return try {
            val algorithm: Algorithm = Algorithm.HMAC256(secret)

            JWT.require(algorithm)
                .withIssuer("forum-api")
                .build()//Cria uma instância nova e reutilizável do JWTVerifier com a configuração já fornecida.
                .verify(token)
                .subject //Retorna o usuário registrado no token
        } catch (ex: JWTVerificationException) {
            throw JWTVerificationException("Token validation error", ex)
        }

    }

    private fun generateExpirationDate(): Instant {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")) //timezone de Brasília
    }

}
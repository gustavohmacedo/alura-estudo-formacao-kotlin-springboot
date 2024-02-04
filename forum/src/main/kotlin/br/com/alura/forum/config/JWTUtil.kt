package br.com.alura.forum.config

import br.com.alura.forum.service.impl.UserDetailImpl

interface JWTUtil {

    fun generateToken(user: UserDetailImpl): String
    fun validateToken(token: String): String
}
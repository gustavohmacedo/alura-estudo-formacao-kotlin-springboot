package br.com.alura.forum.entity

import javax.persistence.*

@Entity
@Table(name = "tb_author")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String
)

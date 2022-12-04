package br.com.alura.forum.entity

import javax.persistence.*

@Entity
@Table(name = "tb_course")
data class Course(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val category: String
)

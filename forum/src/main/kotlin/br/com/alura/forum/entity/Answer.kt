package br.com.alura.forum.entity

import javax.persistence.*

@Entity
@Table(name = "tb_answer")
class Answer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val message: String,
    @ManyToOne
    val topic: Topic,
    @ManyToOne
    val author: User,
    val solution: Boolean
)

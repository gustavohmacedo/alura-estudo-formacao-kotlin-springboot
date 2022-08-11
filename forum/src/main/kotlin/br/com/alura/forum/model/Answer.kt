package br.com.alura.forum.model

data class Answer(
    val id: Long,
    val message: String,
    val topic: Topic,
    val author: User,
    val solution: Boolean
)

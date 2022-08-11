package br.com.alura.forum.model

import java.time.LocalDateTime

data class Topic(
    val id: Long,
    val title: String,
    val message: String,
    val creationDate: LocalDateTime = LocalDateTime.now(),
    val status: TopicStatus = TopicStatus.NOT_ANSWERED,
    val author: User,
    val course: Course,
    val answer: List<Answer> = ArrayList()
)

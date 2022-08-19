package br.com.alura.forum.dto

import br.com.alura.forum.entity.TopicStatus
import java.time.LocalDateTime

data class TopicView(
    val id: Long,
    var title: String,
    var message: String,
    val status: TopicStatus,
    val creationDate: LocalDateTime,
)
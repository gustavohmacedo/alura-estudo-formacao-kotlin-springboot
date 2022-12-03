package br.com.alura.forum.dto

import br.com.alura.forum.entity.Topic
import br.com.alura.forum.enumeration.TopicStatus
import java.time.LocalDateTime

data class TopicResponseDTO(
    val id: Long,
    var title: String,
    var message: String,
    val status: TopicStatus,
    val creationDate: LocalDateTime,
)

fun Topic.toTopicResponseDTO() = TopicResponseDTO(
    id = this.id!!,
    title = this.title,
    message = this.message,
    status = this.status!!,
    creationDate = this.creationDate!!
)

fun TopicResponseDTO.toTopicEntity() = Topic(
    id = this.id,
    title = this.title,
    message = this.message,
    creationDate = this.creationDate,
    status = this.status
)
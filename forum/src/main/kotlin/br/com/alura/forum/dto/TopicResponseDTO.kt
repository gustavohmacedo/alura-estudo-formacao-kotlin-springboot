package br.com.alura.forum.dto

import br.com.alura.forum.entity.Topic
import br.com.alura.forum.enumeration.TopicStatus
import java.time.LocalDateTime

data class TopicResponseDTO(
    val id: Long? = null,
    var title: String,
    var message: String,
    val creationDate: LocalDateTime?,
    val status: TopicStatus?,
    val author: UserResponseDTO,
    val course: CourseResponseDTO
)

fun Topic.toTopicResponseDTO() = TopicResponseDTO(
    id = this.id,
    title = this.title,
    message = this.message,
    creationDate = this.creationDate,
    status = this.status,
    author = this.author.toUserResponseDTO(),
    course = this.course.toCourseResponseDTO()
)

fun TopicResponseDTO.toTopicEntity() = Topic(
    id = this.id,
    title = this.title,
    message = this.message,
    creationDate = this.creationDate,
    status = this.status,
    author = this.author.toUserEntity(),
    course = this.course.toCourseEntity()
)
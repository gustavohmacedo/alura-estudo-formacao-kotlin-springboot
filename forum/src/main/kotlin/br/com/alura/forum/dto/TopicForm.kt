package br.com.alura.forum.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class TopicForm(
    @field:NotBlank(message = "course can not be blank")
    val title: String,
    @field:NotBlank(message = "message can not be blank")
    val message: String,
    @field:NotNull(message = "courseId can not be null")
    val courseId: Long,
    @field:NotNull(message = "authorId can not be null")
    val authorId: Long,
)



























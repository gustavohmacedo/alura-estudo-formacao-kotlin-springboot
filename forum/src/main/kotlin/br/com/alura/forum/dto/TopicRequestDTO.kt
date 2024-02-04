package br.com.alura.forum.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class TopicRequestDTO(
   @field:NotBlank(message = "title can not be blank")
   @field:Size(min = 5, max = 100, message = " must be between 5 and 100 characters")
   val title: String,
   @field:NotBlank(message = "message can not be blank")
   @field:Size(min = 5, max = 100, message = " must be between 5 and 100 characters")
   val message: String,
   @field:NotNull(message = "courseId can not be null")
   val courseId: Long,
   @field:NotNull(message = "authorId can not be null")
   val authorId: Long
)

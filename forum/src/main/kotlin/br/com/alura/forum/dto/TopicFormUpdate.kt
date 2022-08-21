package br.com.alura.forum.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TopicFormUpdate(
    @field:NotBlank(message = "course can not be blank")
    @field:Size(min = 5, max = 100, message = "must be between 5 and 100 characters")
    var title: String,
    @field:NotBlank(message = "message can not be blank")
    @field:Size(min = 5, max = 100, message = "must be between 5 and 100 characters")
    var message: String,
)
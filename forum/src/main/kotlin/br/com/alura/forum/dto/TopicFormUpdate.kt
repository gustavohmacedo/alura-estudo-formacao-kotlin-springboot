package br.com.alura.forum.dto

import javax.validation.constraints.NotBlank

data class TopicFormUpdate(
    @field:NotBlank(message = "course can not be blank")
    var title: String,
    @field:NotBlank(message = "message can not be blank")
    var message: String,
)
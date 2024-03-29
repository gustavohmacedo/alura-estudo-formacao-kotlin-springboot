package br.com.alura.forum.dto

import br.com.alura.forum.entity.Course

data class CourseResponseDTO(
    val id: Long? = null,
    val name: String,
    val category: String
)

fun Course.toCourseResponseDTO() = CourseResponseDTO(
    id = this.id,
    name = this.name,
    category = this.category
)

fun CourseResponseDTO.toCourseEntity() = Course(
    id = this.id,
    name = this.name,
    category = this.category
)


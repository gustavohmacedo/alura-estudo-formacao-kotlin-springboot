package br.com.alura.forum.service

import br.com.alura.forum.dto.CourseResponseDTO

interface CourseService {
    fun getById(id: Long): CourseResponseDTO
}
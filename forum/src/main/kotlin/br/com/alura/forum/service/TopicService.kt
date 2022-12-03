package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicByCategoryResponseDTO
import br.com.alura.forum.dto.TopicRequestDTO
import br.com.alura.forum.dto.TopicResponseDTO
import br.com.alura.forum.dto.TopicUpdateRequestDTO
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TopicService {
    fun save(topicRequest: TopicRequestDTO): TopicResponseDTO
    fun getAll(nameCourse: String?, pagination: Pageable): Page<TopicResponseDTO>
    fun getById(id: Long): TopicResponseDTO
    fun update(id: Long, topicUpdate: TopicUpdateRequestDTO): TopicResponseDTO
    fun delete(id: Long)
    fun getReport(): List<TopicByCategoryResponseDTO>

}
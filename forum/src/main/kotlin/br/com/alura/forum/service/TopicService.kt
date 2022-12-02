package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicByCategoryResponseDTO
import br.com.alura.forum.dto.TopicResponseDTO
import br.com.alura.forum.dto.TopicUpdateRequestDTO
import br.com.alura.forum.entity.Topic
import org.springframework.data.domain.Page

interface TopicService {
    fun save(topic: Topic): TopicResponseDTO
    fun getAll(): Page<TopicResponseDTO>
    fun getById(id: Long): TopicResponseDTO
    fun update(id: Long, topic: TopicUpdateRequestDTO): TopicResponseDTO
    fun delete(id: Long)
    fun getReport(): List<TopicByCategoryResponseDTO>

}
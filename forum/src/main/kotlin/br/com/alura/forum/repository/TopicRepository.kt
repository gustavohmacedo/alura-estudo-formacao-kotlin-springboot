package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicByCategoryResponseDTO
import br.com.alura.forum.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(nameCourse: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new br.com.alura.forum.dto.TopicByCategoryResponseDTO(course.category, count(t)) FROM Topic t JOIN t.course course GROUP BY course.category")
    fun getReportTopicsQuantityByCategory(): List<TopicByCategoryResponseDTO>

}


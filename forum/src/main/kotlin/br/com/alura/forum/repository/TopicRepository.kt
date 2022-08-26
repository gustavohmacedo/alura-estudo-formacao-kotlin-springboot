package br.com.alura.forum.repository

import br.com.alura.forum.entity.Topic
import org.springframework.data.jpa.repository.JpaRepository

interface TopicRepository : JpaRepository<Topic, Long> {

    fun findByCourseName(nameCourse: String): List<Topic>
}


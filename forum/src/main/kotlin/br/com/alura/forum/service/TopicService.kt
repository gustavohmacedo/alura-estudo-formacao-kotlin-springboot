package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicByCategoryDto
import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicFormUpdate
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicService(
    private val topicRepository: TopicRepository,
    private val topicFormMapper: TopicFormMapper,
    private val topicViewMapper: TopicViewMapper,
    private val notFoundMessage: String = "Topic not found!"
) {

    fun save(form: TopicForm): TopicView {
        val topic = topicFormMapper.map(form)
        topicRepository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun getAll(
        nameCourse: String?,
        pagination: Pageable,
    ): Page<TopicView> {
        val topics = if (nameCourse == null) {
            topicRepository.findAll(pagination)
        } else {
            topicRepository.findByCourseName(nameCourse, pagination)
        }
        return topics.map { it ->
            topicViewMapper.map(it)
        }
    }

    fun getById(id: Long): TopicView {
        val topic: Topic = topicRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicViewMapper.map(topic)
    }

    fun update(id: Long, form: TopicFormUpdate): TopicView {
        val topic: Topic = topicRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        topic.title = form.title
        topic.message = form.message
        topicRepository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun delete(id: Long) {
        val topic: Topic = topicRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        topicRepository.delete(topic)
    }

    fun getReport(): List<TopicByCategoryDto> {
        return topicRepository.getReportTopicsQuantityByCategory()
    }
}
package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.*
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.repository.TopicRepository
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicServiceImpl(
    private val topicRepository: TopicRepository,
    private val courseService: CourseService,
    private val userService: UserService,
    private val notFoundMessage: String = "Topic not found!"
) {

    fun save(dto: TopicRequestDTO): TopicResponseDTO {
        val course = courseService.getById(dto.courseId)
        val author = userService.getById(dto.authorId)
        var topic = Topic(
            title = dto.title,
            message = dto.message,
            course = course.toCourseEntity(),
            author = author.toUserEntity()
        )
        return topicRepository.save(topic).toTopicResponseDTO()

    }

    fun getAll(
        nameCourse: String?,
        pagination: Pageable,
    ): Page<TopicResponseDTO> {
        val topics = if (nameCourse != null) {
            topicRepository.findByCourseName(nameCourse, pagination)
        } else {
            topicRepository.findAll(pagination)
        }
        return topics.map { it ->
            topicViewMapper.map(it)
        }
    }

    fun getById(id: Long): TopicResponseDTO {
        val topic: Topic = topicRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topicViewMapper.map(topic)
    }

    fun update(id: Long, form: TopicUpdateRequestDTO): TopicResponseDTO {
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

    fun getReport(): List<TopicByCategoryResponseDTO> {
        return topicRepository.getReportTopicsQuantityByCategory()
    }
}
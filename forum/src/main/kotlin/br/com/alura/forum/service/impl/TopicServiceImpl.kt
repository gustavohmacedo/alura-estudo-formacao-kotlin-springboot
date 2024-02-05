package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.*
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.repository.TopicRepository
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.TopicService
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
) : TopicService {

    override fun save(topicRequest: TopicRequestDTO): TopicResponseDTO {
        val course = courseService.getCourseById(topicRequest.courseId)
        val author = userService.getUserById(topicRequest.authorId)

        val topic = Topic(
            title = topicRequest.title,
            message = topicRequest.message,
            course = course.toCourseEntity(),
            author = author.toUserEntity()
        )

        return topicRepository.save(topic).toTopicResponseDTO()

    }

    override fun getAll(
        nameCourse: String?,
        pagination: Pageable,
    ): Page<TopicResponseDTO> {
        val topics = if (nameCourse != null) {
            topicRepository.findByCourseName(nameCourse, pagination)
        } else {
            topicRepository.findAll(pagination)
        }

        return topics.map {
            it.toTopicResponseDTO()
        }
    }

    override fun getTopicById(id: Long): TopicResponseDTO {
        val topic: Topic = topicRepository
            .findById(id).orElseThrow { NotFoundException(notFoundMessage) }
        return topic.toTopicResponseDTO()
    }

    override fun update(id: Long, topicUpdate: TopicUpdateRequestDTO): TopicResponseDTO {
        getTopicById(id).let {
            val topic = it.copy(
                title = topicUpdate.title,
                message = topicUpdate.message
            )

            return topicRepository.save(topic.toTopicEntity()).toTopicResponseDTO()
        }

    }

    override fun delete(id: Long) {
        val topic = this.getTopicById(id)
        topicRepository.delete(topic.toTopicEntity())
    }

    override fun getReport(): List<TopicByCategoryResponseDTO> {
        return topicRepository.getReportTopicsQuantityByCategory()
    }
}
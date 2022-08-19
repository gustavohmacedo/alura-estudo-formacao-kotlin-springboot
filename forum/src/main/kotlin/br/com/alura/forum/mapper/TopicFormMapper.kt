package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val courseService: CourseService,
    private val userService: UserService,
) : Mapper<TopicForm, Topic> {
    override fun map(form: TopicForm): Topic {
        return Topic(
            title = form.title,
            message = form.message,
            course = courseService.getById(form.courseId),
            author = userService.getById(form.authorId)
        )
    }
}





package br.com.alura.forum.service

import br.com.alura.forum.entity.Course
import br.com.alura.forum.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CourseService(
    private val courseRepository: CourseRepository
) {
    fun getById(id: Long): Course {
        val optionalCourse: Optional<Course> = courseRepository.findById(id)
        if (optionalCourse.isEmpty) {
            RuntimeException("Course is not found")
        }
        return optionalCourse.get()
    }

}
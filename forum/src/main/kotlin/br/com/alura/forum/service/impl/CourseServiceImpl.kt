package br.com.alura.forum.service.impl

import br.com.alura.forum.dto.CourseResponseDTO
import br.com.alura.forum.dto.toCourseResponseDTO
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.repository.CourseRepository
import br.com.alura.forum.service.CourseService
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    private val courseRepository: CourseRepository,
) : CourseService {

    override fun getCourseById(id: Long): CourseResponseDTO {
        val course = courseRepository.findById(id)
        if (course.isEmpty) {
            throw NotFoundException("Course not found")
        }

        return course.get().toCourseResponseDTO()
    }

}
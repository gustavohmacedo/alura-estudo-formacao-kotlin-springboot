package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicByCategoryResponseDTO
import br.com.alura.forum.dto.TopicRequestDTO
import br.com.alura.forum.dto.TopicUpdateRequestDTO
import br.com.alura.forum.dto.TopicResponseDTO
import br.com.alura.forum.service.impl.TopicServiceImpl
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/topics")
class TopicController(
    private val topicService: TopicServiceImpl,
) {

    @PostMapping
    @Transactional
    fun saveTopic(
        @RequestBody @Valid form: TopicRequestDTO,
    ): ResponseEntity<TopicResponseDTO> {
        val topicView = topicService.save(form)
        return ResponseEntity.status(HttpStatus.CREATED).body(topicView)
    }

    @GetMapping
    fun getAllTopics(
        @RequestParam(required = false) nameCourse: String?,
        @PageableDefault(
            size = 5, sort = ["creationDate"],
            direction = Sort.Direction.DESC
        ) pagination: Pageable,
    ): ResponseEntity<Page<TopicResponseDTO>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                topicService.getAll(nameCourse, pagination)
            )
    }

    @GetMapping("/{id}")
    fun getTopicById(
        @PathVariable id: Long,
    ): ResponseEntity<TopicResponseDTO> {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getById(id))
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateTopic(
        @RequestBody @Valid form: TopicUpdateRequestDTO,
        @PathVariable id: Long,
    ): ResponseEntity<TopicResponseDTO> {
        val topicView = topicService.update(id, form)
        return ResponseEntity.status(HttpStatus.OK).body(topicView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deleteTopic(
        @PathVariable id: Long,
    ) {
        topicService.delete(id)
    }

    @GetMapping("/report")
    fun getTopicsQuantityByCategory(): ResponseEntity<List<TopicByCategoryResponseDTO>> {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getReport())
    }

}
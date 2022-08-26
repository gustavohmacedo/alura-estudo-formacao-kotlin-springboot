package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicFormUpdate
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.service.TopicService
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
@RequestMapping("/topics")
class TopicController(
    private val topicService: TopicService,
) {

    @PostMapping
    @Transactional
    fun saveTopic(
        @RequestBody @Valid form: TopicForm,
    ): ResponseEntity<TopicView> {
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
    ): ResponseEntity<Page<TopicView>> {
        return ResponseEntity.status(HttpStatus.OK)
            .body(
                topicService.getAll(nameCourse, pagination)
            )
    }

    @GetMapping("/{id}")
    fun getTopicById(
        @PathVariable id: Long,
    ): ResponseEntity<TopicView> {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.getById(id))
    }

    @PutMapping("/{id}")
    @Transactional
    fun updateTopic(
        @RequestBody @Valid form: TopicFormUpdate,
        @PathVariable id: Long,
    ): ResponseEntity<TopicView> {
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

}
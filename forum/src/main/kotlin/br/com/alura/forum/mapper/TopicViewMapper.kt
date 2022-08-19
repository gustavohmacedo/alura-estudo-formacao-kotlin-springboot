package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.entity.Topic
import org.springframework.stereotype.Component

@Component
class TopicViewMapper : Mapper<Topic, TopicView> {
    override fun map(topic: Topic): TopicView {
        return TopicView(
            id = topic.id!!,
            title = topic.title,
            message = topic.message,
            status = topic.status!!,
            creationDate = topic.creationDate!!
        )
    }
}
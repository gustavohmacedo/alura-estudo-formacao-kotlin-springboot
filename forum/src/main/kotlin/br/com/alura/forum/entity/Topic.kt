package br.com.alura.forum.entity

import java.time.LocalDateTime
import javax.persistence.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "tb_topic")
data class Topic(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String,
    var message: String,
    val creationDate: LocalDateTime? = LocalDateTime.now(),
    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus? = TopicStatus.NOT_ANSWERED,
    @ManyToOne
    var author: User,
    @ManyToOne
    var course: Course,
    @OneToMany(mappedBy = "topic")
    val answers: List<Answer>? = ArrayList()
)







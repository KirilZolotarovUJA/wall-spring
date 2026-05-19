package es.ujaen.wall.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
class Post(
    @ManyToOne(optional = false)
    @JoinColumn(name = "poster_id", nullable = false)
    var poster: Poster? = null,

    @Column(nullable = false, length = 256)
    var content: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Post) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "Post(id=$id, poster=$poster)"
}

data class PostCreateRequest(
    @field:NotNull
    val posterId: Long? = null,

    @field:NotBlank
    @field:Size(max = 256)
    val content: String? = null,
)

data class PostUpdateRequest(
    @field:NotBlank
    @field:Size(max = 256)
    val content: String? = null,
)

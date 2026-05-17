package es.ujaen.wall.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
class Post(
    @Column(nullable = false, length = 32)
    var author: String = "",

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

    override fun toString(): String = "Post(id=$id, author=$author)"
}

data class PostCreateRequest(
    @field:NotBlank
    @field:Size(max = 32)
    val author: String = "",

    @field:NotBlank
    @field:Size(max = 256)
    val content: String = "",
)

data class PostUpdateRequest(
    @field:NotBlank
    @field:Size(max = 256)
    val content: String = "",
)

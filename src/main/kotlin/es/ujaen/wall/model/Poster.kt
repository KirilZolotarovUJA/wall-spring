package es.ujaen.wall.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Entity
class Poster(
    @Column(nullable = false, length = 32)
    var username: String = "",

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @OneToMany(mappedBy = "poster", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnore
    var posts: MutableList<Post> = mutableListOf(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Poster) return false
        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    override fun toString(): String = "Poster(id=$id, username=$username)"
}

data class PosterCreateRequest(
    @field:NotBlank
    @field:Size(max = 32)
    val username: String? = null,
)

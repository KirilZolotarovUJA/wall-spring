package es.ujaen.wall.repository

import es.ujaen.wall.model.Poster
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PosterRepository : ListCrudRepository<Poster, Long> {
    fun findByUsername(username: String): Poster?
}

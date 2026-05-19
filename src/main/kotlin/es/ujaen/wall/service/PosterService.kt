package es.ujaen.wall.service

import es.ujaen.wall.exception.PosterNotFoundException
import es.ujaen.wall.model.Poster
import es.ujaen.wall.model.PosterCreateRequest
import es.ujaen.wall.repository.PosterRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PosterService(private val posterRepository: PosterRepository) {
    fun getAllPosters(): List<Poster> {
        return posterRepository.findAll()
    }

    @Transactional
    fun createPoster(request: PosterCreateRequest): Poster {
        val (username) = request
        return posterRepository.save(Poster(username!!))
    }

    fun getPosterById(id: Long): Poster {
        return posterRepository.findByIdOrNull(id) ?: throw PosterNotFoundException(id)
    }

    @Transactional
    fun deletePosterById(id: Long) {
        val poster = getPosterById(id)
        posterRepository.delete(poster)
    }
}

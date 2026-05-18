package es.ujaen.wall.controller

import es.ujaen.wall.model.Poster
import es.ujaen.wall.model.PosterCreateRequest
import es.ujaen.wall.service.PosterService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/poster")
class PosterController(private val posterService: PosterService) {
    @GetMapping
    fun getAllPosters(): List<Poster> = posterService.getAllPosters()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPoster(@Valid @RequestBody requestBody: PosterCreateRequest): Poster =
        posterService.createPoster(requestBody)

    @GetMapping("/{id}")
    fun getPoster(@PathVariable id: Long): Poster = posterService.getPosterById(id)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePoster(@PathVariable id: Long) = posterService.deletePosterById(id)
}

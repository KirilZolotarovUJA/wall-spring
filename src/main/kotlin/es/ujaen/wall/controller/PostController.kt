package es.ujaen.wall.controller

import es.ujaen.wall.model.Post
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.model.PostUpdateRequest
import es.ujaen.wall.service.PostService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController(private val postService: PostService) {
    @GetMapping
    fun getAllPosts(): List<Post> = postService.getAllPosts()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(@Valid @RequestBody requestBody: PostCreateRequest): Post = postService.createPost(requestBody)

    @GetMapping("/{id}")
    fun getPost(@PathVariable id: Long): Post = postService.getPostById(id)

    @PutMapping("/{id}")
    fun updatePost(@Valid @RequestBody requestBody: PostUpdateRequest, @PathVariable id: Long): Post =
        postService.updatePostById(id, requestBody)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(@PathVariable id: Long) = postService.deletePostById(id)
}

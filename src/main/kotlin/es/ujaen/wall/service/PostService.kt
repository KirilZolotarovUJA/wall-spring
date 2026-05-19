package es.ujaen.wall.service

import es.ujaen.wall.exception.PostNotFoundException
import es.ujaen.wall.exception.PosterNotFoundException
import es.ujaen.wall.model.Post
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.model.PostUpdateRequest
import es.ujaen.wall.repository.PostRepository
import es.ujaen.wall.repository.PosterRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
    private val posterRepository: PosterRepository,
) {
    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    @Transactional
    fun createPost(request: PostCreateRequest): Post {
        val (posterId, content) = request
        val poster = posterRepository.findByIdOrNull(posterId!!) ?: throw PosterNotFoundException(posterId)
        checkCurrentPoster(poster.username)
        return postRepository.save(Post(poster, content!!))
    }

    fun getPostById(id: Long): Post {
        return postRepository.findByIdOrNull(id) ?: throw PostNotFoundException(id)
    }

    @Transactional
    fun updatePostById(id: Long, request: PostUpdateRequest): Post {
        val (content) = request
        val post = getPostById(id)
        checkCurrentPoster(post.poster!!.username)
        post.content = content!!
        return post
    }

    @Transactional
    fun deletePostById(id: Long) {
        val post = getPostById(id)
        checkCurrentPoster(post.poster!!.username)
        postRepository.delete(post)
    }

    private fun checkCurrentPoster(username: String) {
        val currentUsername = SecurityContextHolder.getContext().authentication?.name
        if (currentUsername != username) {
            throw AccessDeniedException("Only the poster can modify their posts")
        }
    }
}

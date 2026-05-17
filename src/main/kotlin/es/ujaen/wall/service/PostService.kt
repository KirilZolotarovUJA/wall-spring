package es.ujaen.wall.service

import es.ujaen.wall.exception.PostNotFoundException
import es.ujaen.wall.model.Post
import es.ujaen.wall.model.PostCreateRequest
import es.ujaen.wall.model.PostUpdateRequest
import es.ujaen.wall.repository.PostRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(private val postRepository: PostRepository) {
    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }

    @Transactional
    fun createPost(request: PostCreateRequest): Post {
        val (author, content) = request
        return postRepository.save(Post(author, content))
    }

    fun getPostById(id: Long): Post {
        return postRepository.findByIdOrNull(id) ?: throw PostNotFoundException(id)
    }

    @Transactional
    fun updatePostById(id: Long, request: PostUpdateRequest): Post {
        val (content) = request
        val post = getPostById(id)
        post.content = content
        return post
    }

    @Transactional
    fun deletePostById(id: Long) {
        val post = getPostById(id)
        postRepository.delete(post)
    }
}

package es.ujaen.wall.repository

import es.ujaen.wall.model.Post
import org.springframework.data.repository.ListCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : ListCrudRepository<Post, Long>

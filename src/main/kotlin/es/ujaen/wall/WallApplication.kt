package es.ujaen.wall

import es.ujaen.wall.model.Post
import es.ujaen.wall.model.Poster
import es.ujaen.wall.repository.PostRepository
import es.ujaen.wall.repository.PosterRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WallApplication {
    @Bean
    fun seedRunner(postRepository: PostRepository, posterRepository: PosterRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String> ->
            val poster = Poster("CyZ")
            val post = Post(poster = poster, content = "Hello, world!")

            posterRepository.save(poster)
            postRepository.save(post)

            println(poster)
            println(post)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<WallApplication>(*args)
}

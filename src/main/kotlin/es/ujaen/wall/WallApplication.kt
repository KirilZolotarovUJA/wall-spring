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
    fun posterRunner(repository: PosterRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String> ->
            val poster = Poster("CyZ")

            repository.save(poster)
            println(poster)

            val saved: Poster? = repository.findById(poster.id!!).orElseThrow { NoSuchElementException() }
            println(saved)
        }
    }

    @Bean
    fun postRunner(repository: PostRepository): CommandLineRunner {
        return CommandLineRunner { _: Array<String> ->
            val post = Post("CyZ", "Hello, world!")

            repository.save(post)
            println(post)

            val saved: Post? = repository.findById(post.id!!).orElseThrow { NoSuchElementException() }
            println(saved)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<WallApplication>(*args)
}

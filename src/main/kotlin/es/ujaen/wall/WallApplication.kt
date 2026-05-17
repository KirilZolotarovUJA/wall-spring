package es.ujaen.wall

import es.ujaen.wall.model.Post
import es.ujaen.wall.repository.PostRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class WallApplication {
    @Bean
    fun runner(repository: PostRepository): CommandLineRunner {
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

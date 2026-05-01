package es.ujaen.wall

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WallApplication

fun main(args: Array<String>) {
    runApplication<WallApplication>(*args)
}

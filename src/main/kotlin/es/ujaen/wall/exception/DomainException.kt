package es.ujaen.wall.exception

sealed class DomainException(message: String) : RuntimeException(message)

class PostNotFoundException(val id: Long) : DomainException("Post #$id not found")

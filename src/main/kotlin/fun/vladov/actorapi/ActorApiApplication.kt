package `fun`.vladov.actorapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ActorApiApplication

fun main(args: Array<String>) {
    runApplication<ActorApiApplication>(*args)
}

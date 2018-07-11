package `fun`.vladov.actorapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ActorApiApplication

fun main(args: Array<String>) {
    runApplication<ActorApiApplication>(*args)
}

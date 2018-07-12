package `fun`.vladov.actorapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * base init spring class
 */
@SpringBootApplication
open class ActorApiApplication

/**
 * init context & run app
 */
fun main(args: Array<String>) {
    runApplication<ActorApiApplication>(*args)
}

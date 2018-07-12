package `fun`.vladov.actorapi.repository

import `fun`.vladov.actorapi.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @author vladov
 * 12/07/2018
 */
interface UserRepository : MongoRepository<User, String> {
    fun findByUsername(username: String): User
}
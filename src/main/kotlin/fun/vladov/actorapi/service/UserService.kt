package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.User

/**
 * @author vladov
 * 12/07/2018
 */
interface UserService {

    fun createUser(user: User)
}
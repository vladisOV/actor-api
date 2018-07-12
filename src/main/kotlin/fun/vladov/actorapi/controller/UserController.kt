package `fun`.vladov.actorapi.controller

import `fun`.vladov.actorapi.domain.User
import `fun`.vladov.actorapi.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * @author vladov
 * 12/07/2018
 */
@RestController("/user")
class UserController(@Autowired val userService: UserService) {

    @PostMapping
    fun register(@Valid user: User) {
        userService.createUser(user)
    }
}
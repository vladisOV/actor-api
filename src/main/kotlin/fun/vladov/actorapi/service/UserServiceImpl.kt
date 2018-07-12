package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.User
import `fun`.vladov.actorapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * @author vladov
 * 12/07/2018
 */
@Service
class UserServiceImpl(@Autowired val userRepository: UserRepository,
                      @Autowired val passwordEncoder: BCryptPasswordEncoder) : UserService {

    override fun createUser(user: User) {
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
    }
}
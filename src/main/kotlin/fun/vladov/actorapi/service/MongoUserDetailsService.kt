package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author vladov
 * 12/07/2018
 */
@Component
class MongoUserDetailsService(@Autowired val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
        val authorities = Arrays.asList(SimpleGrantedAuthority("user"))
        return org.springframework.security.core.userdetails.User(user.username, user.password, authorities)
    }
}
package `fun`.vladov.actorapi.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


/**
 * @author vladov
 * 12/07/2018
 */
@Configuration
@EnableConfigurationProperties
class SecurityConfiguration : WebSecurityConfigurerAdapter() {
    @Autowired
    lateinit var mongoUserDetailsService: UserDetailsService

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/generate").authenticated()
                .antMatchers("/user").permitAll()
                .and().httpBasic()
                .and().sessionManagement().disable()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(mongoUserDetailsService)
    }
}
package me.szydelko.jwtspringbootkotlin.security

import me.szydelko.jwtspringbootkotlin.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

@Configuration
class ApplicationConfig {

    @Bean
    fun userDetailsService(userService: UserService) : UserDetailsService{

        return UserDetailsService {

            username ->  userService.findByEmail(username).orElseThrow { UsernameNotFoundException("user not found") }

        }

    }

}
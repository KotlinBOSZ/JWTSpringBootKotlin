package me.szydelko.jwtspringbootkotlin.security

import me.szydelko.jwtspringbootkotlin.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class ApplicationConfig {


    @Bean
    fun userDetailsService(userService: UserService) : UserDetailsService{

        return UserDetailsService {

            username ->  userService.findByEmail(username).orElseThrow { UsernameNotFoundException("user not found") }

        }

    }

    @Bean
    fun authenticationProvider(userDetailsService: UserDetailsService,passwordEncoder: PasswordEncoder) :AuthenticationProvider{

        val authProvider = DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;

    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager;
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder();
    }


}
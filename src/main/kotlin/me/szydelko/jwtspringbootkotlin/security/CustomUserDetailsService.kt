package me.szydelko.jwtspringbootkotlin.security

import me.szydelko.jwtspringbootkotlin.repostory.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        if (username == null) throw UsernameNotFoundException("Username is null");
        val user = userRepository.findByName(username).orElseThrow{UsernameNotFoundException("Username not found")}
        return User(user.name,user.password,user.roles.map { SimpleGrantedAuthority(it.name) })
    }

}
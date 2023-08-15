package me.szydelko.jwtspringbootkotlin.service

import me.szydelko.jwtspringbootkotlin.dto.LoginDTO
import me.szydelko.jwtspringbootkotlin.dto.RegisterDTO
import me.szydelko.jwtspringbootkotlin.mapper.ToEntity
import me.szydelko.jwtspringbootkotlin.model.User
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthService(
    private val userService: UserService,
    @Qualifier("registerDTOUserToEntity") private val registerDTOUserToEntity: ToEntity<RegisterDTO, User>,
    private val authenticationManager: AuthenticationManager,
) {


    fun register(registerDTO: RegisterDTO): Optional<User> {

        return userService.save(registerDTOUserToEntity.toEntity(registerDTO)!!)

    }

//    @Deprecated("no")
//    fun login(loginDTO: LoginDTO): String {
//
//        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(loginDTO.username,loginDTO.password))
//        SecurityContextHolder.getContext().authentication = authentication
//        return "success"
//    }

}

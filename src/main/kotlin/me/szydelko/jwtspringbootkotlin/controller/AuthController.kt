package me.szydelko.jwtspringbootkotlin.controller

import me.szydelko.jwtspringbootkotlin.dto.AuthResponse
import me.szydelko.jwtspringbootkotlin.dto.LoginDTO
import me.szydelko.jwtspringbootkotlin.dto.RegisterDTO
import me.szydelko.jwtspringbootkotlin.service.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@RequestBody registerDTO: RegisterDTO) : ResponseEntity<String> {
       if (authService.register(registerDTO).isPresent){
          return ResponseEntity<String>("success",HttpStatus.OK)
       }
        return ResponseEntity("error",HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody loginDTO : LoginDTO) : ResponseEntity<AuthResponse> {

        

    }

}
package me.szydelko.jwtspringbootkotlin.dto

data class RegisterDTO(
    val username: String,
    val email: String,
    val password: String
)

package me.szydelko.jwtspringbootkotlin.model

import jakarta.persistence.*

@Entity
@Table(name = "roles")
data class Role(
    @Id
    @Column(unique = true)
    val name: String,
)

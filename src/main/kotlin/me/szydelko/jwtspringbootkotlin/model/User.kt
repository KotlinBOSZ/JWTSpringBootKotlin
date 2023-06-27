package me.szydelko.jwtspringbootkotlin.model

import jakarta.persistence.*
@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column(unique = true)
    var name: String,
    @Column(unique = true)
    var email: String,
    var password: String,
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")], inverseJoinColumns = [JoinColumn(name = "role", referencedColumnName = "name")])
    var roles: Set<Role>
)
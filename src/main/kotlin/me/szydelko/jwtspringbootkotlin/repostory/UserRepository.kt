package me.szydelko.jwtspringbootkotlin.repostory

import me.szydelko.jwtspringbootkotlin.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface UserRepository : JpaRepository<User, Long>{
    fun findByNameAndEmail(name: String, email: String): Optional<User>

    fun findByIdOrEmail(id: Long, email: String): Optional<User>

    fun findByEmail(email: String): Optional<User>
    fun findByName(name: String): Optional<User>
    fun existsByEmail(email: String): Boolean
    fun existsByIdOrEmail(id: Long, email: String): Boolean

}
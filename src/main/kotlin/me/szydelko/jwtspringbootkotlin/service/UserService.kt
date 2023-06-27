package me.szydelko.jwtspringbootkotlin.service


import me.szydelko.jwtspringbootkotlin.model.User
import me.szydelko.jwtspringbootkotlin.repostory.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(private val userRepository: UserRepository){

    fun save(user: User): Optional<User> {
        if (user.id!=null || userRepository.existsByEmail(user.email) || userRepository.existsByEmail(user.name)) return Optional.empty()
        return Optional.of(userRepository.save(user));
    }
    fun update(user: User): Optional<User> {
        if (user.id == null ||!userRepository.existsById(user.id)) return Optional.empty();
        return Optional.of(userRepository.save(user));

    }

    fun existsByEmail(email: String): Boolean{
        return userRepository.existsByEmail(email)
    }

    fun existsById(id: Long): Boolean{
        return userRepository.existsById(id)
    }

    fun existsByIdOrEmail(id: Long,email: String): Boolean{
        return userRepository.existsByIdOrEmail(id,email)
    }

    fun findById(id: Long): Optional<User>{
        return userRepository.findById(id)
    }

    fun findByIdOrEmail(id: Long?, email: String): Optional<User>{
        if (id == null) return userRepository.findByEmail(email)
        return userRepository.findByIdOrEmail(id,email)
    }

}
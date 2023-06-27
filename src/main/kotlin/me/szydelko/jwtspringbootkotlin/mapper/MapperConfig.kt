package me.szydelko.jwtspringbootkotlin.mapper

import me.szydelko.jwtspringbootkotlin.dto.RegisterDTO
import me.szydelko.jwtspringbootkotlin.dto.UserDTO
import me.szydelko.jwtspringbootkotlin.model.User
import me.szydelko.jwtspringbootkotlin.service.RoleService
import me.szydelko.jwtspringbootkotlin.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class MapperConfig() {

    @Bean
    fun userDTOUserMapper(userService: UserService) : Mapper<UserDTO,User>{

        return object: Mapper<UserDTO,User>{
            override fun fromEntity(entity: User): UserDTO {
                return UserDTO(entity.id,entity.name,entity.email)
            }

            override fun toEntity(domain: UserDTO): User {
                return userService.findByIdOrEmail(domain.id,domain.email).orElse(User(domain.id,domain.name,domain.email,"", mutableSetOf()))
            }

        }

    }
    @Bean
    fun registerDTOUserToEntity(roleService: RoleService,passwordEncoder: PasswordEncoder) : ToEntity<RegisterDTO,User>{

        return object : ToEntity<RegisterDTO,User>{
            override fun toEntity(domain: RegisterDTO): User {
               return User(null,domain.username,domain.email,passwordEncoder.encode(domain.password), roleService.existsFilterString(setOf("USER")))
            }

        }

    }


}
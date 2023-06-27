package me.szydelko.jwtspringbootkotlin.repostory

import me.szydelko.jwtspringbootkotlin.model.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role,String>{
}
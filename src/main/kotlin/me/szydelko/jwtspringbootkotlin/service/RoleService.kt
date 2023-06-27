package me.szydelko.jwtspringbootkotlin.service

import me.szydelko.jwtspringbootkotlin.model.Role
import me.szydelko.jwtspringbootkotlin.repostory.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

    fun save(name: String){
        if (!roleRepository.existsById(name))
           roleRepository.save(Role(name))
    }

    fun exists(name: String): Boolean{
        return roleRepository.existsById(name)
    }

    fun existsFilterString(roles:Set<String>):Set<Role>{
        return existsFilter(roles.map { Role(it) }.toSet())
    }

    fun existsFilter(roles:Set<Role>):Set<Role>{
        return roles.filter { exists(it.name) }.toMutableSet()
    }

    fun getIfExists(name: String): Role?{
        return roleRepository.findById(name).orElse(null);
    }


}
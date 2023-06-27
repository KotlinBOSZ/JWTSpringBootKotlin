package me.szydelko.jwtspringbootkotlin

import me.szydelko.jwtspringbootkotlin.service.RoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener


@SpringBootApplication
class JwtSpringBootKotlinApplication

fun main(args: Array<String>) {
    runApplication<JwtSpringBootKotlinApplication>(*args)
}

@Autowired
private lateinit var roleService: RoleService

@EventListener(ApplicationReadyEvent::class)
fun DB() {

    roleService.save("ADMIN")
    roleService.save("USER")

}



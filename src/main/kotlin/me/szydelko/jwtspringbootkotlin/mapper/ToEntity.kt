package me.szydelko.jwtspringbootkotlin.mapper

interface ToEntity<D,E> {
    fun toEntity(domain: D): E?

}
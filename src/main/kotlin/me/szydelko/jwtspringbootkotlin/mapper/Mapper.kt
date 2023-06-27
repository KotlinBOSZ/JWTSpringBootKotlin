package me.szydelko.jwtspringbootkotlin.mapper

interface Mapper<D,E> : ToEntity<D,E> {
    fun fromEntity(entity: E): D?
    override fun toEntity(domain: D): E?

}
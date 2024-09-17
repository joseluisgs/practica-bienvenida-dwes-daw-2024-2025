package org.example.repositories.crud

interface CrudRepository<T, ID> {
    fun save(entity: T) : T
    fun findAll(): List<T>
    fun findById(id: ID): T?
    fun update(entity: T) : T?
    fun delete(id: ID) : T?
}
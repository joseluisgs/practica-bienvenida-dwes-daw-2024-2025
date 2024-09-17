package org.example.repositories.crud

interface CrudRepository <T, ID> {
    fun findAll(): List<T>
    fun findById(id: Long): T?
    fun save(value: T): T
    fun update(id: ID, value: T): T?
    fun delete(id: ID): T?

}
package org.example.tenist.repository

import org.example.tenist.models.Tenist

interface TenistRepository {
    fun create(tenist : Tenist) : Tenist?
    fun delete(id : Int, logical : Boolean = false) : Tenist?
    fun update(tenist : Tenist) : Tenist?
    fun get(id : Int) : Tenist?
    fun getAll() : List<Tenist>
}
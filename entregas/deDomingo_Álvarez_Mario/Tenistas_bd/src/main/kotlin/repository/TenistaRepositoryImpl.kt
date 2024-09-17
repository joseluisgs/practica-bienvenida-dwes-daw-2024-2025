package org.example.tenistas.repository

import org.example.tenistas.models.Tenista

class TenistaRepositoryImpl(
/*private val queries: TenistaQueries*/
) : TenistaRepository {

    /*override fun findAll(): List<Tenista> {
        return queries.selectAllTenistas().executeAsList()
    }

    override fun findById(id: Int): Tenista? {
        return queries.selectTenistaById(id).executeAsOneOrNull()
    }

    override fun save(tenista: Tenista): Tenista {
        queries.insertTenista(
            tenista.nombre,
            tenista.pais,
            tenista.altura.toDouble(),
            tenista.peso.toDouble(),
            tenista.puntos,
            tenista.mano.toString(),
            tenista.fechaNacimiento.toString(),
            tenista.createdAt.toString(),
            tenista.updatedAt.toString()
        )
        return tenista
    }

    override fun update(tenista: Tenista): Tenista {
        queries.updateTenista(
            tenista.nombre,
            tenista.pais,
            tenista.altura.toDouble(),
            tenista.peso.toDouble(),
            tenista.puntos,
            tenista.mano.toString(),
            tenista.fechaNacimiento.toString(),
            tenista.updatedAt.toString(),
            tenista.id
        )
        return tenista
    }

    override fun delete(id: Int): Boolean {
        queries.deleteTenista(id)
        return true
    }*/
    override fun findAll(): List<Tenista> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Tenista? {
        TODO("Not yet implemented")
    }

    override fun save(tenista: Tenista): Tenista {
        TODO("Not yet implemented")
    }

    override fun update(tenista: Tenista): Tenista {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}

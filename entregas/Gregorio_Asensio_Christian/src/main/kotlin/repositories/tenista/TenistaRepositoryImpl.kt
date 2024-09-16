package org.example.repositories.tenista

import models.Tenista
import org.example.services.database.DataBaseManager
import java.sql.Connection
import java.sql.Statement

//class TenistaRepositoryImpl(private val connection: Connection) : TenistaRepository {
class TenistaRepositoryImpl() : TenistaRepository {

    override fun findByCountry(pais: String): List<Tenista> {
        TODO("Not yet implemented")
    }

    override fun findByRanking(): List<Tenista> {
        TODO("Not yet implemented")
    }

    override fun save(entity: Tenista): Tenista {

        var tenista: Tenista? = null;
        //var timeStamp: LocalDateTime.now();

        val sql = """
    INSERT INTO tenista (id, nombre, pais, altura, peso, puntos, mano, fechaNacimiento, created_at, updated_at)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, datetime('now'), datetime('now'))
"""

        DataBaseManager.use { db ->
            val statement = db.connection?.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)!!
            //statement.setLong(1, entity.id)
            statement.setString(2, entity.nombre)
            statement.setString(3, entity.pais)
            statement.setInt(4, entity.altura)
            statement.setInt(5, entity.peso)
            statement.setInt(6, entity.puntos)
            statement.setString(7, entity.mano.toString())
            statement.setString(8, entity.fechaNacimiento.toString())
            statement.executeUpdate()

            // Ejecutamos la consulta
            statement.executeUpdate()
            // Procesamos el id del tenista
            tenista = entity.copy(
                id = statement.generatedKeys.getLong(1)
            )
        }

        return tenista!!
    }

    override fun findAll(): List<Tenista> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Tenista? {
        TODO("Not yet implemented")
    }

    override fun update(entity: Tenista): Tenista {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int): Tenista? {
        TODO("Not yet implemented")
    }
}
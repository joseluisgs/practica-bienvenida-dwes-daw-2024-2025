package org.example.tenista.repository

import org.example.tenista.models.Tenista
import dev.tomas.tenista.repository.TenistaRepository
import org.example.service.DataBaseManager
import org.example.tenista.util.DBUtils
import org.example.tenista.util.DBUtils.toTenista
import org.lighthousegames.logging.logging
import java.sql.PreparedStatement
import java.time.LocalDateTime

private val logger = logging()

class TenistasRepositoryImpl : TenistaRepository {

    override fun findAll(): List<Tenista> {
        logger.debug { "Obteniendo todos los tenistas" }
        val result = mutableListOf<Tenista>()
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM tenistas"
            val stmt = db.connection?.prepareStatement(sql)
            val rs = stmt?.executeQuery()
            if (rs != null) {
                while (rs.next()) {
                    result.add(rs.toTenista())
                }
            }
        }
        return result
    }

    override fun findById(id: Long): Tenista? {
        logger.debug { "Obteniendo tenista por id: $id" }
        var result: Tenista? = null
        DataBaseManager.use { db ->
            val sql = "SELECT * FROM tenistas WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)
            stmt?.setLong(1, id)
            val rs = stmt?.executeQuery()
            if (rs != null) {
                if (rs.next()) {
                    result = rs.toTenista()
                }
            }
        }
        return result
    }

    override fun saveOrUpdate(tenista: Tenista) {
        // Si el tenista tiene un ID, intentamos encontrarlo en la base de datos.
        val existingTenista = tenista.id?.let { findById(it) }
        if (existingTenista != null) {
            // Si el tenista existe, lo actualizamos.
            update(tenista.id!!, tenista)
        } else {
            // Si el tenista no existe, lo guardamos como nuevo.
            save(tenista)
        }
    }


    override fun save(tenista: Tenista): Tenista {
        logger.debug { "Guardando tenista: $tenista" }
        val timeStamp = LocalDateTime.now()
        var result: Tenista = tenista
        DataBaseManager.use { db ->
            val sql =
                "INSERT INTO tenistas (nombre, pais, altura, peso, puntos, mano, fecha_nacimiento, created_at, updated_at, ranking) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
            val stmt = db.connection?.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
            if (stmt != null) {
                DBUtils.prepareStatementForTenista(stmt, tenista, timeStamp)
                val generatedKey = DBUtils.executeUpdateAndGetGeneratedKey(stmt)
                if (generatedKey != null) {
                    result = tenista.copy(
                        id = generatedKey,
                        createdAt = timeStamp,
                        updatedAt = timeStamp
                    )
                }
            }
        }
        return result
    }

    override fun update(id: Long, tenista: Tenista): Tenista? {
        logger.debug { "Actualizando tenista por id: $id" }
        val existingTenista = this.findById(id) ?: return null
        var result: Tenista = existingTenista
        DataBaseManager.use { db ->
            val sql =
                "UPDATE tenistas SET nombre = ?, pais = ?, altura = ?, peso = ?, puntos = ?, mano = ?, fecha_nacimiento = ?, updated_at = ?, ranking = ? WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)
            if (stmt != null) {
                DBUtils.prepareStatementForTenista(stmt, tenista, LocalDateTime.now())
                stmt.setInt(9, tenista.ranking)
                stmt.setLong(10, id)
                val affectedRows = stmt.executeUpdate()
                if (affectedRows > 0) {
                    result = tenista.copy(
                        id = id,
                        updatedAt = LocalDateTime.now()
                    )
                }
            }
        }
        return result
    }


    override fun delete(id: Long): Tenista? {
        logger.debug { "Borrando tenista por id: $id" }
        val existingTenista = this.findById(id) ?: return null
        var result: Tenista = existingTenista
        DataBaseManager.use { db ->
            val sql = "DELETE FROM tenistas WHERE id = ?"
            val stmt = db.connection?.prepareStatement(sql)
            if (stmt != null) {
                stmt.setLong(1, id)
                val affectedRows = stmt.executeUpdate()
                if (affectedRows > 0) {
                    result = result.copy(updatedAt = LocalDateTime.now())
                }
            }
        }
        return result
    }
}

package org.example.tenista.util

import org.example.tenista.models.Mano
import org.example.tenista.models.Tenista
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDateTime

object DBUtils {
    fun prepareStatementForTenista(stmt: PreparedStatement, tenista: Tenista, timeStamp: LocalDateTime) {
        stmt.setString(1, tenista.nombre)
        stmt.setString(2, tenista.pais)
        stmt.setInt(3, tenista.altura)
        stmt.setDouble(4, tenista.peso)
        stmt.setInt(5, tenista.puntos)
        stmt.setString(6, tenista.mano.name)
        stmt.setDate(7, java.sql.Date.valueOf(tenista.fechaNacimiento))
        stmt.setTimestamp(8, java.sql.Timestamp.valueOf(timeStamp))
        stmt.setTimestamp(9, java.sql.Timestamp.valueOf(timeStamp))
    }

    fun executeUpdateAndGetGeneratedKey(stmt: PreparedStatement): Long? {
        val affectedRows = stmt.executeUpdate()
        if (affectedRows > 0) {
            val generatedKeys = stmt.generatedKeys
            if (generatedKeys.next()) {
                return generatedKeys.getLong(1)
            }
        }
        return null
    }

    fun ResultSet.toTenista(): Tenista {
        return Tenista(
            id = getLong("id"),
            nombre = getString("nombre"),
            pais = getString("pais"),
            altura = getInt("altura"),
            peso = getDouble("peso"),
            puntos = getInt("puntos"),
            mano = Mano.valueOf(getString("mano")),
            fechaNacimiento = getDate("fecha_nacimiento").toLocalDate(),
            createdAt = getTimestamp("created_at").toLocalDateTime(),
            updatedAt = getTimestamp("updated_at").toLocalDateTime()
        )
    }
}
package org.example.tenistas.models

import java.time.LocalDate
import java.time.LocalDateTime

class Tenista(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: ManoTenista,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    override fun toString(): String {
        return "Tenista(id=$id, nombre=$nombre, pais=$pais, altura=$altura, peso=$peso, puntos=$puntos, mano=$mano, fechaNacimiento=$fechaNacimiento, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
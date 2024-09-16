package org.example.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Tenista(
    val id: Long = -1L,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: Mano,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDate = LocalDate.now(),
    val updatedAt: LocalDate = LocalDate.now()
)
{
    override fun toString(): String {
        return "Tenista(id=$id, nombre='$nombre', pais='$pais', altura=$altura, peso=$peso, puntos=$puntos, mano=$mano, fecha nac=$fechaNacimiento, createdAt=$createdAt, updatedAt=$updatedAt)"
    }
}
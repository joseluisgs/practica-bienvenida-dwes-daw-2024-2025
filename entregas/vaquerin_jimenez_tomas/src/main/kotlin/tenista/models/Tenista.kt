package org.example.tenista.models

import java.time.LocalDate
import java.time.LocalDateTime


data class Tenista(
    val id: Long? = null,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Double,
    val puntos: Int,
    val mano: Mano,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val ranking: Int = 0
)

enum class Mano {
    DIESTRO, ZURDO
}
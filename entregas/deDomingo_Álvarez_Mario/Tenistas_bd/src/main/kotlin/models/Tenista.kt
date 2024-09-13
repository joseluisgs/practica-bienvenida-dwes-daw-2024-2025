package org.example.tenistas.models

import org.example.models.Mano
import java.time.LocalDateTime
import java.time.LocalDate

data class Tenista(
    val id: Int = 0,
    val nombre: String,
    val pais: String,
    val altura: Double,
    val peso: Double,
    val puntos: Int,
    val mano: Mano,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
package org.example.tenistas.dto

import org.example.models.Mano
import java.time.LocalDate
import java.time.LocalDateTime
import org.example.tenistas.models.Mano

data class TenistaDTO(
    val id: Int = 0,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: Mano,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

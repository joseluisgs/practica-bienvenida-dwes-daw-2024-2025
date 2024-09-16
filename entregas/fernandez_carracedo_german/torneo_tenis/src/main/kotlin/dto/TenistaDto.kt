package org.example.dto

import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@Serializable
data class TenistaDto (
    val id: Long = -1,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fechaNacimiento: String =  LocalDate.now().toString(),
    val createdAt: String = LocalDate.now().toString(),
    val updatedAt: String = LocalDate.now().toString()
)
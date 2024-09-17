package org.example.dto

// DTO para Tenista
data class TenistaDto(
    val id: Long,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val mano: String,
    val puntos: Int,
    val createdAt: String,
    val updatedAt: String?,
    val isDeleted: Boolean
)
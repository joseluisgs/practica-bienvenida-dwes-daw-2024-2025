package org.example.tenistas.dto

import kotlinx.serialization.Serializable

@Serializable
data class TenistaDtoRank(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fechaNacimiento: String,
    val createdAt: String,
    val updatedAt: String,
    val ranking: Int
)
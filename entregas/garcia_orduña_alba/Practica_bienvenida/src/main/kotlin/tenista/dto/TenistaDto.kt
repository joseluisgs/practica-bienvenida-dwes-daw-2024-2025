package org.example.persona.dto

import kotlinx.serialization.Serializable
import org.example.persona.models.ManoTenista
import java.time.LocalDateTime

@Serializable
data class TenistaDto(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: ManoTenista,
    val fecha_nacimiento: String,
    val created_at:String,
    val updated_at: String,

    ) {
}
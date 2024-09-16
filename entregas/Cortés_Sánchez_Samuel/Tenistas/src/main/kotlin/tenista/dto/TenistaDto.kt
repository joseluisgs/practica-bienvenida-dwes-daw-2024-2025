package tenistas.dto

import kotlinx.serialization.Serializable

@Serializable
data class TenistaDto(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fecha_nacimiento: String,
    val created_at: String,
    val updated_at: String
)
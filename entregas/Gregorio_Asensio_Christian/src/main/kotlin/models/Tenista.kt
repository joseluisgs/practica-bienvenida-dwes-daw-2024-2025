package models

import java.time.LocalDateTime

data class Tenista(
    val id: Long = 0,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fechaNacimiento: String,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?
)

enum class Mano {
    DIESTRO, ZURDO
}

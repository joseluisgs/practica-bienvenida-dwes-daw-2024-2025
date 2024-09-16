package tenista.models

import java.time.LocalDate
import java.time.LocalDateTime

class Tenista(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: String,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fecha_nacimiento: LocalDate,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime,
)
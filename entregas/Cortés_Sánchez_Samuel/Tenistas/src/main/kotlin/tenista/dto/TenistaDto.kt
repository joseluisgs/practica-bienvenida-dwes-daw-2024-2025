package tenista.dto

data class TenistaDto(
    val id: String,
    val nombre: String,
    val pais: String,
    val altura: String,
    val peso: String,
    val puntos: String,
    val mano: String,
    val fecha_nacimiento: String,
    val created_at: String,
    val updated_at: String
)
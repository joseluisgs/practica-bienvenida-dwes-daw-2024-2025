package tenistas.dto

/**
 * Clase de datos que representa un tenista en formato de transferencia de datos (DTO).
 *
 * @property id Identificador único del tenista.
 * @property nombre Nombre completo del tenista.
 * @property pais País de origen del tenista.
 * @property altura Altura del tenista en centímetros.
 * @property peso Peso del tenista en kilogramos.
 * @property puntos Puntos obtenidos por el tenista en su ranking.
 * @property mano Mano dominante del tenista (derecha o izquierda).
 * @property fecha_nacimiento Fecha de nacimiento del tenista en formato de cadena (por ejemplo, "YYYY-MM-DD").
 * @property created_at Fecha y hora en que el registro fue creado en formato de cadena.
 * @property updated_at Fecha y hora en que el registro fue actualizado por última vez en formato de cadena.
 * @author Natalia González
 * @version 1.0
 */
data class TenistaDto(
    val id: String,
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
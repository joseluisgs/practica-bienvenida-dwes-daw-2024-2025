package tenistas.models

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Representa a un tenista con todos los datos relevantes asociados.
 *
 * @property id Identificador único del tenista.
 * @property nombre Nombre completo del tenista.
 * @property pais País de origen del tenista.
 * @property altura Altura del tenista en centímetros.
 * @property peso Peso del tenista en kilogramos.
 * @property puntos Puntos obtenidos por el tenista en su ranking.
 * @property mano Mano dominante del tenista, representada por el enum [TipoMano].
 * @property fecha_nacimiento Fecha de nacimiento del tenista.
 * @property created_at Fecha y hora en que el registro del tenista fue creado.
 * @property updated_at Fecha y hora de la última actualización del registro del tenista.
 * @author Natalia González
 * @version 1.0
 */
data class Tenista(
    val id: String,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: TipoMano,
    val fecha_nacimiento: LocalDate,
    val created_at: LocalDateTime,
    val updated_at: LocalDateTime
)
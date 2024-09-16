package tenistas.dto

import kotlinx.serialization.Serializable

/**
 * Clase que representa el DTO de un tenista.
 * @property id Identificador del tenista.
 * @property nombre Nombre del tenista.
 * @property pais País del tenista.
 * @property altura Altura del tenista.
 * @property peso Peso del tenista.
 * @property puntos Puntos del tenista.
 * @property mano Mano hábil del tenista.
 * @property fecha_nacimiento Fecha de nacimiento del tenista.
 * @property created_at Fecha de creación del tenista.
 * @property updated_at Fecha de actualización del tenista.
 * @author Javier Hernández
 * @since 1.0
 */
@Serializable
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
    val updated_at: String,
)
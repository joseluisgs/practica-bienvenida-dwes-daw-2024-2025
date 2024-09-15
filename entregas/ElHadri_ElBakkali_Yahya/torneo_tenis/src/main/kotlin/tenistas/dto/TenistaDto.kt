package tenistas.dto

import kotlinx.serialization.Serializable
import tenistas.models.MANO
import tenistas.models.Tenista
import tenistas.models.Tenista.Companion.generarFechaActual
import tenistas.models.Tenista.Companion.idAutonumerico
import java.time.LocalDate

@Serializable
data class TenistaDto (
    val id:String,
    val nombre:String,
    val pais:String,
    val altura:Int,
    val peso:Int,
    val puntos:Int,
    val mano: String,
    val fechaNacimiento: String,
    val created_at:String = Tenista.generarFechaActual(),
    var updated_at: String = Tenista.generarFechaActual()
)
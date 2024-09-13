package tenistas.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Tenista(
    val id:Long = idAutonumerico(),
    val nombre:String,
    val pais:String,
    val altura:Int,
    val peso:Int,
    val puntos:Int,
    val mano:MANO,
    val fechaNacimiento:LocalDate,
    val created_at: String = generarFechaActual(),
    var updated_at: String = generarFechaActual()
) {
    companion object {
        private var contadorId = 0

        fun idAutonumerico(): Long {
            contadorId += 1
            return contadorId.toLong()
        }
        fun generarFechaActual(): String {
            val ahora = LocalDateTime.now()
            val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
            return ahora.format(formato)
        }
    }
}
enum class MANO{
    ZURDO,DIESTRO
}



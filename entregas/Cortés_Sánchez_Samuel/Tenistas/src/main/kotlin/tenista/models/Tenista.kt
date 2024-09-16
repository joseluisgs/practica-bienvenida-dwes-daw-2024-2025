package tenista.models

import java.time.LocalDate
import java.time.LocalDateTime

class Tenista(
    val id: Int,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: String,
    val fecha_nacimiento: LocalDate,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val updated_at: LocalDateTime = LocalDateTime.now()
) {
    override fun toString(): String {
        return "Tenista(id=$id, nombre=$nombre, pais=$pais, altura=$altura, peso=$peso, puntos=$puntos, mano=$mano, fechaNacimiento=$fecha_nacimiento, createdAt=$created_at, updatedAt=$updated_at)"
    }
}
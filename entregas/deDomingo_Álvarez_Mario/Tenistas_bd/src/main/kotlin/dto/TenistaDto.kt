import org.example.models.Mano
import java.time.LocalDate
import java.time.LocalDateTime

data class TenistaDTO(
    val id: Int = 0,
    val nombre: String,
    val pais: String,
    val altura: Int,
    val peso: Int,
    val puntos: Int,
    val mano: Mano,
    val fechaNacimiento: LocalDate,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null
)

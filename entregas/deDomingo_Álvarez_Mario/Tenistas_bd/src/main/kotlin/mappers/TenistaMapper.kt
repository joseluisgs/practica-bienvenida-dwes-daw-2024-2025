import org.example.models.Mano
import org.example.tenistas.models.Tenista
import java.time.LocalDateTime

object TenistaMapper {
    fun toDTO(tenista: Tenista): TenistaDTO {
        return TenistaDTO(
            id = tenista.id,
            nombre = tenista.nombre,
            pais = tenista.pais,
            altura = tenista.altura,
            peso = tenista.peso,
            puntos = tenista.puntos,
            mano = tenista.mano,
            fechaNacimiento = tenista.fechaNacimiento,
            createdAt = tenista.createdAt,
            updatedAt = tenista.updatedAt
        )
    }

    fun fromDTO(dto: TenistaDTO): Tenista {
        return Tenista(
            id = dto.id,
            nombre = dto.nombre,
            pais = dto.pais,
            altura = dto.altura,
            peso = dto.peso,
            puntos = dto.puntos,
            mano = dto.mano,
            fechaNacimiento = dto.fechaNacimiento,
            createdAt = dto.createdAt ?: LocalDateTime.now(),
            updatedAt = dto.updatedAt ?: LocalDateTime.now()
        )
    }

    fun elegirMano(s: String): Mano? {
        return when (s) {
            "DIESTRO" -> Mano.DIESTRO
            "ZURDO" -> Mano.ZURDO
            else -> Mano.DIESTRO
        }
    }
}

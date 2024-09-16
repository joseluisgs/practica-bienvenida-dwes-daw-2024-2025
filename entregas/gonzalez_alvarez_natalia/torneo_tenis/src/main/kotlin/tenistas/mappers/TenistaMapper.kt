package tenistas.mappers

import database.TenistaEntity
import tenistas.dto.TenistaDto
import tenistas.models.Tenista
import tenistas.models.TipoMano
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * Convierte un objeto [TenistaEntity] en un objeto [Tenista].
 *
 * @return Un objeto [Tenista] con los mismos datos que el objeto [TenistaEntity].
 * @author Natalia González
 * @version 1.0
 */
fun TenistaEntity.toTenista(): Tenista {
    return Tenista(
        id = this.id.toString(),
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura.toInt(),
        peso = this.peso.toInt(),
        puntos = this.puntos.toInt(),
        mano = TipoMano.valueOf(this.mano),
        fecha_nacimiento = LocalDate.parse(this.fecha_nacimiento),
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at)
    )
}

/**
 * Convierte un objeto [TenistaDto] en un objeto [Tenista].
 *
 * @return Un objeto [Tenista] con los mismos datos que el objeto [TenistaDto].
 * @author Natalia González
 * @version 1.0
 */
fun TenistaDto.toTenista(): Tenista {
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = TipoMano.valueOf(this.mano),
        fecha_nacimiento = LocalDate.parse(this.fecha_nacimiento),
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at)
    )
}

/**
 * Convierte un objeto [Tenista] en un objeto [TenistaDto].
 *
 * @return Un objeto [TenistaDto] con los mismos datos que el objeto [Tenista].
 * @author Natalia González
 * @version 1.0
 */
fun Tenista.toTenistaDto(): TenistaDto {
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano.toString(),
        fecha_nacimiento = this.fecha_nacimiento.toString(),
        created_at = this.created_at.toString(),
        updated_at = this.updated_at.toString()
    )
}

/**
 * Convierte una lista de objetos [TenistaDto] a una lista de objetos [Tenista].
 *
 * @return Lista de objetos [Tenista] correspondientes a los objetos [TenistaDto].
 * @author Natalia González
 * @version 1.0
 */
fun List<TenistaDto>.toTenistaList(): List<Tenista> {
    return map { it.toTenista() }
}

/**
 * Convierte una lista de objetos [Tenista] a una lista de objetos [TenistaDto].
 *
 * @return Lista de objetos [TenistaDto] correspondientes a los objetos [Tenista].
 * @author Natalia González
 * @version 1.0
 */
fun List<Tenista>.toTenistaDtoList(): List<TenistaDto> {
    return map { it.toTenistaDto() }
}
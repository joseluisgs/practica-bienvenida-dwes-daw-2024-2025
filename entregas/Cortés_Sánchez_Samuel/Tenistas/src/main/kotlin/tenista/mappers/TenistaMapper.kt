package tenista.mappers

import database.TenistaEntity

import org.lighthousegames.logging.logging
import tenista.models.Tenista
import tenistas.dto.TenistaDto
import java.time.LocalDate
import java.time.LocalDateTime

private val logger = logging()

fun TenistaEntity.toTenista(): Tenista {
    logger.debug { "Mapeando TenistaEntity a Tenista" }
    return Tenista(
        id = this.id.toInt(),
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura.toInt(),
        peso = this.peso.toInt(),
        puntos = this.puntos.toInt(),
        mano = this.mano,
        fecha_nacimiento = LocalDate.parse(this.fecha_nacimiento),
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at)
    )
}

fun TenistaDto.toTenista(): Tenista {
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fecha_nacimiento = LocalDate.parse(this.fecha_nacimiento),
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at)
    )
}
package org.example.tenista.mappers
import database.TenistaEntity
import org.example.persona.dto.TenistaDto
import org.example.persona.models.Tenista
import org.lighthousegames.logging.logging
import java.time.LocalDateTime


private val logger = logging()
fun Tenista.toTenistaDto(): TenistaDto {
    logger.debug { "Mapeando Tenista a TenistaDto" }
    return TenistaDto(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fecha_nacimiento = this.fecha_nacimiento.toString(),
        created_at = this.created_at.toString(),
        updated_at = this.updated_at.toString()
    )
}

fun TenistaDto.toTenista(): Tenista {
    logger.debug { "Mapeando TenistaDto a Tenista" }
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fecha_nacimiento =LocalDateTime.parse(this.fecha_nacimiento) ,
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at),
    )
}
fun TenistaEntity.toTenista(): Tenista{
    logger.debug { "Mappeando TenistaEntity to Tenista" }
    return Tenista(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fecha_nacimiento =LocalDateTime.parse(this.fecha_nacimiento) ,
        created_at = LocalDateTime.parse(this.created_at),
        updated_at = LocalDateTime.parse(this.updated_at),
    )
}

fun Tenista.toTenistaEntity(): TenistaEntity{
    logger.debug { "Mappeando TenistaEntity to Tenista" }
    return TenistaEntity(
        id = this.id,
        nombre = this.nombre,
        pais = this.pais,
        altura = this.altura,
        peso = this.peso,
        puntos = this.puntos,
        mano = this.mano,
        fecha_nacimiento = LocalDateTime.parse(this.fecha_nacimiento.toString()).toString(),
        created_at = LocalDateTime.parse(this.created_at.toString()).toString(),
        updated_at = LocalDateTime.parse(this.updated_at.toString()).toString(),
    )
}
